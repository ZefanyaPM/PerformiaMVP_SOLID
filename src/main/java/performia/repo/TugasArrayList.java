/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package performia.repo;

import java.util.ArrayList;
import java.util.List;
import performia.model.Tugas;

/**
 *
 * @author Zefa
 */
public class TugasArrayList implements ITugasRepo{
    private final List<Tugas> listTugas = new ArrayList<>();
    
    @Override
    public void simpan(Tugas tugas){
        listTugas.add(tugas);
    }
    
    @Override
    public int totalRiwayat(){
        return listTugas.size();
    }
    
    @Override
    public Tugas getByIndex(int index){
        return listTugas.get(index);
    }
}
