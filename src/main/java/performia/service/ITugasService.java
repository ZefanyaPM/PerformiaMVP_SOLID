package performia.service;

import java.util.List;

import performia.model.Tugas;

public interface ITugasService {
    String STATUS_WAITING_FOR_REVIEW = "Waiting for Review";
    String STATUS_DONE = "Done";
    
    List<Tugas> getTugasKaryawan(int index);

    Tugas submitTugas(int index, int taskIndex, String linkBukti);

    List<Tugas> getTugasAktif(int karyawanIndex);

    List<Tugas> getTugasJatuhTempoHariIni(int karyawanIndex);

    List<Tugas> getTugasJatuhTempoMingguIni(int karyawanIndex);
}
