/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package performia.app;

import java.time.LocalDate;
import java.util.List;

import performia.model.Pengumuman;
import performia.repo.IKaryawanRepo;
import performia.repo.IManajerRepo;
import performia.repo.IPengumumanRepo;
import performia.repo.KaryawanArrayList;
import performia.repo.ManajerArrayList;
import performia.repo.PengumumanArrayList;
import performia.user.Karyawan;
import performia.user.Manajer;
/**
 *
 * @author ARTHES
 */
public class PerformiaData {
    private static final PerformiaData INSTANCE = new PerformiaData();
    private boolean initialized;

    private final IKaryawanRepo karyawanRepo;
    private final IManajerRepo manajerRepo;
    private final IPengumumanRepo pengumumanRepo;

    public PerformiaData() {
        this(new KaryawanArrayList(), new ManajerArrayList(), new PengumumanArrayList());
    }

    public PerformiaData(
            IKaryawanRepo karyawanRepo,
            IManajerRepo manajerRepo,
            IPengumumanRepo pengumumanRepo
    ) {
        this.karyawanRepo = karyawanRepo;
        this.manajerRepo = manajerRepo;
        this.pengumumanRepo = pengumumanRepo;
    }

    public static PerformiaData getInstance() {
        if (!INSTANCE.initialized) {
            INSTANCE.initDummyData();
        }
        return INSTANCE;
    }

    public void initDummyData() {
        manajerRepo.bersihkan();
        karyawanRepo.bersihkan();
        pengumumanRepo.bersihkan();
        initialized = false;

        manajerRepo.tambah(new Manajer("M01", "Anti", "anti@mail", "anti123"));

        Karyawan k1 = new Karyawan("K01", "Arthes", "arthes@mail", "arthes123", "IT", 1100);
        Karyawan k2 = new Karyawan("K02", "Faiz", "faiz@mail", "faiz123", "IT", 1200);
        Karyawan k3 = new Karyawan("K03", "Zefa", "zefa@mail", "zefa123", "IT", 800);
        Karyawan k4 = new Karyawan("K04", "Aziz", "aziz@mail", "aziz123", "IT", 900);
        Karyawan k5 = new Karyawan("K05", "Haikal", "haikal@mail", "haikal123", "IT", 1050);
        karyawanRepo.tambahBatch(List.of(k1, k2, k3, k4, k5));

        for (Karyawan karyawan : karyawanRepo.getList()) {
            karyawan.setTugas("A01", "Laporan", LocalDate.of(2026, 6, 25), 100);
        }

        pengumumanRepo.tambah(new Pengumuman(
                "1",
                "Pengumuman Rapat Divisi",
                "Hari Senin, 22 Juni 2026 pukul 10.00 WIB\n"
                        + "Agenda: Evaluasi kinerja kuartal II dan rencana proyek baru\n"
                        + "Tempat: Ruang Meeting Lantai 2\n"
                        + "Peserta: Seluruh manajer divisi dan tim proyek (harap hadir tepat waktu)"
        ));
        pengumumanRepo.tambah(new Pengumuman(
                "2",
                "Pengumuman Pelatihan Keterampilan Komunikasi",
                "Perusahaan akan mengadakan pelatihan untuk meningkatkan kemampuan komunikasi efektif dalam tim.\n"
                        + "Waktu: Jumat, 19 Juni 2026 pukul 09.00–12.00 WIB di Ruang Training.\n"
                        + "Peserta: Seluruh karyawan divisi pemasaran.\n"
                        + "Mohon membawa laptop pribadi untuk simulasi praktik."
        ));
        pengumumanRepo.tambah(new Pengumuman(
                "3",
                "Peringatan Perbaikan Sistem",
                "Kami akan melakukan perbaikan sistem untuk meningkatkan performa aplikasi.\n"
                        + "Waktu perbaikan: Minggu, 20 Juni 2026 pukul 22.00 – 02.00 WIB.\n"
                        + "Selama periode tersebut, aplikasi tidak dapat diakses.\n"
                        + "Mohon selesaikan tugas atau aktivitas penting sebelum waktu perbaikan dimulai."
        ));
        initialized = true;
    }

    public List<Karyawan> getListKaryawan() {
        return karyawanRepo.getList();
    }

    public List<Manajer> getListManajer() {
        return manajerRepo.getList();
    }

    public List<Pengumuman> getListPengumuman() {
        return pengumumanRepo.getList();
    }

    public IKaryawanRepo getKaryawanRepo() {
        return karyawanRepo;
    }

    public IManajerRepo getManajerRepo() {
        return manajerRepo;
    }

    public IPengumumanRepo getPengumumanRepo() {
        return pengumumanRepo;
    }

    public String getJudulPengumuman(int index) {
        return pengumumanRepo.getJudulPengumuman(index);
    }

    public void setIsiPengumuman(int index, String isi) {
        pengumumanRepo.setIsiPengumuman(index, isi);
    }

    public String getIsiPengumuman(int index) {
        return pengumumanRepo.getIsiPengumuman(index);
    }
}