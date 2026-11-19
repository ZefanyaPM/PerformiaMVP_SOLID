package performia.service;

import java.time.LocalDate;

import performia.model.Performance;
import performia.model.Tugas;
import performia.repo.IKaryawanRepo;
import performia.user.Karyawan;

public class PerformanceService implements IPerformanceService {
    private final IKaryawanRepo karyawanRepo;

    public PerformanceService(IKaryawanRepo karyawanRepo) {
        this.karyawanRepo = karyawanRepo;
    }

    @Override
    public Performance hitungPerformance(int karyawanIndex) {
        if (karyawanIndex < 0 || karyawanIndex >= karyawanRepo.getList().size()) {
            throw new IllegalArgumentException("Karyawan tidak valid.");
        }

        Karyawan karyawan = karyawanRepo.getList().get(karyawanIndex);
        int totalTugas = karyawan.getTotalTugas();
        int tugasSelesai = 0;
        int tugasOnTime = 0;
        int tugasOverdue = 0;
        LocalDate hariIni = LocalDate.now();

        for (int i = 0; i < totalTugas; i++) {
            Tugas tugas = karyawan.getListTugas(i);
            boolean selesai = tugas.getStatus().equalsIgnoreCase(STATUS_DONE);

            if (selesai) {
                tugasSelesai++;
                if (!tugas.getTanggalBatas().isBefore(hariIni)) {
                    tugasOnTime++;
                }
            }

            if (tugas.getTanggalBatas().isBefore(hariIni)
                    && !tugas.getStatus().equalsIgnoreCase(STATUS_DONE)) {
                tugasOverdue++;
            }
        }

        int tugasProgres = Math.max(totalTugas - tugasSelesai, 0);
        Performance performance = new Performance();
        performance.setTugasSelesai(tugasSelesai);
        performance.setTugasProgres(tugasProgres);
        performance.setTotalTugas(totalTugas);
        performance.setTotalXP(karyawan.getTotalXP());
        performance.setTugasOnTime(tugasOnTime);
        performance.setTugasOverdue(tugasOverdue);
        performance.setSkorPerforma(
                performance.hitungSkorPerforma(tugasOnTime, tugasOverdue)
        );
        performance.setOnTimeRate(
                totalTugas == 0 ? 0.0f : tugasOnTime * 100.0f / totalTugas
        );
        return performance;
    }

}
