/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package performia.repo;

import performia.model.Tugas;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Zefa
 */

public class RiwayatTugasArrayList implements IRiwayatTugasRepo{
    private final List<Tugas> listRiwayatTugas = new ArrayList<>();
    
    @Override
    public void simpan(Tugas tugas){
        listRiwayatTugas.add(tugas);
    }
    
    @Override
    public int totalRiwayat(){
        return listRiwayatTugas.size();
    }
    
    @Override
    public Tugas getByIndex(int index){
        return listRiwayatTugas.get(index);
    }
}
