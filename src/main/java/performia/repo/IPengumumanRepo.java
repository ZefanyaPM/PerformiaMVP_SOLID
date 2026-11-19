/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package performia.repo;

import java.util.List;
import performia.model.Pengumuman;

/**
 *
 * @author Zefa
 */
public interface IPengumumanRepo {
    void tambahBatch(List<Pengumuman> batchPengumuman);

    void tambah(Pengumuman pengumuman);

    List<Pengumuman> getList();

    void bersihkan();

    String getJudulPengumuman(int index);
    
    void setIsiPengumuman(int index, String isi);
    
    public String getIsiPengumuman(int index);
}
