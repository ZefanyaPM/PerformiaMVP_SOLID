package performia.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import performia.model.Ranking;
import performia.model.Reward;
import performia.model.RiwayatPenugasan;
import performia.model.Tugas;
import performia.repo.IKaryawanRepo;
import performia.user.Karyawan;

public class KaryawanService implements IKaryawanService {
    private final IKaryawanRepo karyawanRepo;
    private final IRiwayatService riwayatService;

    public KaryawanService(IKaryawanRepo karyawanRepo) {
        this.karyawanRepo = karyawanRepo;
        this.riwayatService = new RiwayatService(karyawanRepo);
    }

    public KaryawanService(IKaryawanRepo karyawanRepo, IRiwayatService riwayatService) {
        this.karyawanRepo = karyawanRepo;
        this.riwayatService = riwayatService;
    }

    @Override
    public List<Ranking> getRanking(String periode) {
        List<Karyawan> listKaryawan = getKaryawanUrutByXP();
        List<Ranking> listRanking = new ArrayList<>();

        for (int i = 0; i < listKaryawan.size(); i++) {
            Karyawan karyawan = listKaryawan.get(i);
            Reward reward = getReward(i);
            listRanking.add(new Ranking(
                    periode,
                    karyawan.getNama(),
                    karyawan.getTotalXP(),
                    reward
            ));
        }

        return listRanking;
    }

    @Override
    public List<Karyawan> getKaryawanUrutByXP() {
        List<Karyawan> listKaryawan = new ArrayList<>(karyawanRepo.getList());
        listKaryawan.sort(Comparator.comparingInt(Karyawan::getTotalXP).reversed());
        return listKaryawan;
    }

    @Override
    public int cariIndexByNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            return -1;
        }

        List<Karyawan> listKaryawan = karyawanRepo.getList();
        for (int i = 0; i < listKaryawan.size(); i++) {
            if (nama.trim().equalsIgnoreCase(listKaryawan.get(i).getNama())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String getNamaKaryawan(int karyawanIndex) {
        return getkaryawan(karyawanIndex).getNama();
    }

    @Override
    public void buatTugas(int karyawanIndex, String idTugas, String judul,
            LocalDate tanggalBatas, int skorXP) {
        Karyawan karyawan = getkaryawan(karyawanIndex);

        if (idTugas == null || idTugas.trim().isEmpty()) {
            throw new IllegalArgumentException("ID tugas wajib diisi.");
        }
        if (judul == null || judul.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul tugas wajib diisi.");
        }
        if (tanggalBatas == null) {
            throw new IllegalArgumentException("Tanggal batas tugas wajib diisi.");
        }
        if (skorXP <= 0) {
            throw new IllegalArgumentException("Skor XP harus lebih dari 0.");
        }

        karyawan.setTugas(
                idTugas.trim(),
                judul.trim(),
                tanggalBatas,
                skorXP
        );
    }

    @Override
    public void buatTugas(int karyawanIndex, String idTugas, String judul,
            String tanggal, String bulan, String tahun, int skorXP) {
        if (tanggal == null || tanggal.trim().isEmpty()
                || bulan == null || bulan.trim().isEmpty()
                || tahun == null || tahun.trim().isEmpty()) {
            throw new IllegalArgumentException("Tanggal, bulan, dan tahun wajib diisi.");
        }

        try {
            int day = Integer.parseInt(tanggal.trim());
            int month = Integer.parseInt(bulan.trim());
            int year = Integer.parseInt(tahun.trim());

            if (month < 1 || month > 12) {
                throw new IllegalArgumentException("Bulan harus di antara 1 - 12.");
            }

            int maximumDay = YearMonth.of(year, month).lengthOfMonth();
            if (day < 1 || day > maximumDay) {
                throw new IllegalArgumentException(
                        "Tanggal tidak valid. Bulan " + month + " tahun " + year
                                + " hanya memiliki " + maximumDay + " hari."
                );
            }

            buatTugas(karyawanIndex, idTugas, judul,
                    LocalDate.of(year, month, day), skorXP);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Tanggal, bulan, dan tahun harus berupa angka.");
        }
    }

    @Override
    public void tambahXPAndRiwayat(int karyawanIndex, int tugasIndex) {
        Karyawan karyawan = getkaryawan(karyawanIndex);
        if (tugasIndex < 0 || tugasIndex >= karyawan.getTotalTugas()) {
            throw new IllegalArgumentException("Tugas tidak valid.");
        }

        Tugas task = karyawan.getListTugas(tugasIndex);
        if (!"Waiting for Review".equalsIgnoreCase(task.getStatus())) {
            throw new IllegalStateException("Tugas belum menunggu review.");
        }

        task.setStatus("Done");
        karyawan.tambahXP(task.getSkorXP());
        riwayatService.tambahRiwayat(karyawanIndex, new RiwayatPenugasan(
                LocalDate.now().toString(),
                task.getJudul(),
                task.getSkorXP()
        ));
    }

    @Override
    public List<TugasReview> getTugasMenungguReview() {
        List<TugasReview> listTugasReview = new ArrayList<>();
        List<Karyawan> listKaryawan = karyawanRepo.getList();

        for (int karyawanIndex = 0; karyawanIndex < listKaryawan.size(); karyawanIndex++) {
            Karyawan karyawan = listKaryawan.get(karyawanIndex);
            for (int tugasIndex = 0; tugasIndex < karyawan.getTotalTugas(); tugasIndex++) {
                Tugas task = karyawan.getListTugas(tugasIndex);
                if ("Waiting for Review".equalsIgnoreCase(task.getStatus())) {
                    listTugasReview.add(new TugasReview(
                            karyawanIndex,
                            tugasIndex,
                            karyawan.getNama(),
                            task.getJudul(),
                            task.getTanggalDikumpulkan(),
                            task.getStatus()
                    ));
                }
            }
        }

        return listTugasReview;
    }

    private Karyawan getkaryawan(int karyawanIndex) {
        List<Karyawan> listKaryawan = karyawanRepo.getList();
        if (karyawanIndex < 0 || karyawanIndex >= listKaryawan.size()) {
            throw new IllegalArgumentException("Karyawan tidak valid.");
        }
        return listKaryawan.get(karyawanIndex);
    }

    private Reward getReward(int index) {
        if (index == 0) {
            return new Reward("R01", "Bonus Rp 1.000.000");
        }
        if (index == 1) {
            return new Reward("R02", "Bonus Rp 500.000");
        }
        if (index == 2) {
            return new Reward("R03", "Bonus Rp 300.000");
        }
        return new Reward("R00", "Tidak ada bonus");
    }
}
