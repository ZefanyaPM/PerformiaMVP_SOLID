/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package performia.repo;

import java.util.ArrayList;
import java.util.List;
import performia.model.Pengumuman;

/**
 *
 * @author Zefa
 */
public class PengumumanArrayList implements IPengumumanRepo{
    private List<Pengumuman> listPengumuman = new ArrayList<>();

    @Override
    public void tambahBatch(List<Pengumuman> batchPengumuman) {
        listPengumuman.addAll(batchPengumuman);
    }

    @Override
    public void tambah(Pengumuman pengumuman) {
        listPengumuman.add(pengumuman);
    }

    @Override
    public List<Pengumuman> getList() {
        return listPengumuman;
    }

    @Override
    public void bersihkan() {
        listPengumuman.clear();
    }
    
    @Override
    public String getJudulPengumuman(int index) {
        return listPengumuman.get(index).getJudul();
    }

    @Override
    public void setIsiPengumuman(int index, String isi) {
        listPengumuman.get(index).setIsi(isi);
    }

    @Override
    public String getIsiPengumuman(int index) {
        return listPengumuman.get(index).getIsi();
    }
}
