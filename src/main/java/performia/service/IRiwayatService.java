package performia.service;

import java.util.List;

import performia.model.RiwayatPenugasan;

public interface IRiwayatService {
    List<RiwayatPenugasan> getRiwayatKaryawan(int karyawanIndex);

    void tambahRiwayat(int karyawanIndex, RiwayatPenugasan riwayat);
}
