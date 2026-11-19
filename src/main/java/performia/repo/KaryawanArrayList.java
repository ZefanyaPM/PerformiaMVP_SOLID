/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package performia.repo;

import java.util.ArrayList;
import java.util.List;
import performia.user.Karyawan;

/**
 *
 * @author Zefa
 */
public class KaryawanArrayList implements IKaryawanRepo{
    private List<Karyawan> listKaryawan = new ArrayList<>();

    @Override
    public void tambahBatch(List<Karyawan> batchKaryawan) {
        listKaryawan.addAll(batchKaryawan);
    }

    @Override
    public void tambah(Karyawan karyawan) {
        listKaryawan.add(karyawan);
    }
    
    @Override
    public List<Karyawan> getList(){
        return listKaryawan;
    }

    @Override
    public void bersihkan() {
        listKaryawan.clear();
    }
    
}
