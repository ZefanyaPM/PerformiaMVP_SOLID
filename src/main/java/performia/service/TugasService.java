package performia.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import performia.model.Tugas;
import performia.repo.IKaryawanRepo;
import performia.user.Karyawan;

public class TugasService implements ITugasService {
    private final IKaryawanRepo karyawanRepo;

    public TugasService(IKaryawanRepo karyawanRepo) {
        this.karyawanRepo = karyawanRepo;
    }

    @Override
    public List<Tugas> getTugasKaryawan(int karyawanIndex) {
        Karyawan karyawan = getKaryawan(karyawanIndex);
        List<Tugas> tasks = new ArrayList<>();

        for (int i = 0; i < karyawan.getTotalTugas(); i++) {
            tasks.add(karyawan.getListTugas(i));
        }

        return tasks;
    }

    @Override
    public Tugas submitTugas(int karyawanIndex, int tugasIndex, String linkBukti) {
        if (linkBukti == null || linkBukti.trim().isEmpty()) {
            throw new IllegalArgumentException("Link bukti tugas wajib diisi.");
        }

        Karyawan karyawan = getKaryawan(karyawanIndex);
        if (tugasIndex < 0 || tugasIndex >= karyawan.getTotalTugas()) {
            throw new IllegalArgumentException("Tugas tidak valid.");
        }

        Tugas tugas = karyawan.getListTugas(tugasIndex);
        if (tugas.getStatus().equalsIgnoreCase(STATUS_DONE)
                || tugas.getStatus().equalsIgnoreCase(STATUS_WAITING_FOR_REVIEW)) {
            throw new IllegalStateException("Tugas sudah tidak dapat disubmit.");
        }

        tugas.setLinkBukti(linkBukti.trim());
        tugas.setTanggalDikumpulkan();
        tugas.setStatus("Waiting for Review");
        return tugas;
    }

    @Override
    public List<Tugas> getTugasAktif(int karyawanIndex) {
        Karyawan karyawan = getKaryawan(karyawanIndex);
        List<Tugas> tugasAktif = new ArrayList<>();
        for (int i = 0; i < karyawan.getTotalTugas(); i++) {
            Tugas tugas = karyawan.getListTugas(i);
            if (!tugas.getStatus().equalsIgnoreCase(STATUS_DONE)) {
                tugasAktif.add(tugas);
            }
        }
        return tugasAktif;
    }

    @Override
    public List<Tugas> getTugasJatuhTempoHariIni(int karyawanIndex) {
        LocalDate hariIni = LocalDate.now();
        List<Tugas> result = new ArrayList<>();
        for (Tugas tugas : getTugasAktif(karyawanIndex)) {
            if (hariIni.equals(tugas.getTanggalBatas())) {
                result.add(tugas);
            }
        }
        return result;
    }

    @Override
    public List<Tugas> getTugasJatuhTempoMingguIni(int karyawanIndex) {
        LocalDate hariIni = LocalDate.now();
        LocalDate akhirMinggu = hariIni.plusWeeks(1);
        List<Tugas> result = new ArrayList<>();
        for (Tugas tugas : getTugasAktif(karyawanIndex)) {
            LocalDate deadline = tugas.getTanggalBatas();
            if (!deadline.isBefore(hariIni) && !deadline.isAfter(akhirMinggu)) {
                result.add(tugas);
            }
        }
        return result;
    }

    private Karyawan getKaryawan(int karyawanIndex) {
        List<Karyawan> listKaryawan = karyawanRepo.getList();
        if (karyawanIndex < 0 || karyawanIndex >= listKaryawan.size()) {
            throw new IllegalArgumentException("Karyawan tidak valid.");
        }
        return listKaryawan.get(karyawanIndex);
    }
}
