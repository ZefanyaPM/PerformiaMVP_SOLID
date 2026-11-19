package performia.service;

import java.time.LocalDate;

public record TugasReview(
        int karyawanIndex,
        int tugasIndex,
        String namaKaryawan,
        String judulTugas,
        LocalDate tanggalSubmit,
        String status
) {
}
