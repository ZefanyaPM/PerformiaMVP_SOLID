package performia.service;

import java.util.ArrayList;
import java.util.List;

import performia.model.RiwayatPenugasan;
import performia.repo.IKaryawanRepo;
import performia.user.Karyawan;

public class RiwayatService implements IRiwayatService {
    private final IKaryawanRepo karyawanRepo;

    public RiwayatService(IKaryawanRepo karyawanRepo) {
        this.karyawanRepo = karyawanRepo;
    }

    @Override
    public List<RiwayatPenugasan> getRiwayatKaryawan(int karyawanIndex) {
        Karyawan karyawan = getKaryawan(karyawanIndex);
        List<RiwayatPenugasan> history = new ArrayList<>();

        for (int i = 0; i < karyawan.getTotalRiwayat(); i++) {
            history.add(karyawan.getListRiwayatPenugasan(i));
        }

        return history;
    }

    @Override
    public void tambahRiwayat(int karyawanIndex, RiwayatPenugasan riwayat) {
        if (riwayat == null) {
            throw new IllegalArgumentException("Riwayat tidak boleh kosong.");
        }

        getKaryawan(karyawanIndex).setRiwayat(riwayat);
    }

    private Karyawan getKaryawan(int karyawanIndex) {
        List<Karyawan> listKaryawan = karyawanRepo.getList();
        if (karyawanIndex < 0 || karyawanIndex >= listKaryawan.size()) {
            throw new IllegalArgumentException("Karyawan tidak valid.");
        }
        return listKaryawan.get(karyawanIndex);
    }
}
