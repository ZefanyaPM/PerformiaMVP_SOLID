package performia.service;

import java.time.LocalDate;
import java.util.List;

import performia.model.Ranking;
import performia.user.Karyawan;

public interface IKaryawanService {
    List<Ranking> getRanking(String periode);

    List<Karyawan> getKaryawanUrutByXP();

    int cariIndexByNama(String nama);

    void buatTugas(int karyawanIndex, String idTugas, String judul,
            LocalDate tanggalBatas, int skorXP);

    void buatTugas(int karyawanIndex, String idTugas, String judul,
            String tanggal, String bulan, String tahun, int skorXP);

    void tambahXPAndRiwayat(int karyawanIndex, int tugasIndex);

    List<TugasReview> getTugasMenungguReview();

    String getNamaKaryawan(int karyawanIndex);
}
