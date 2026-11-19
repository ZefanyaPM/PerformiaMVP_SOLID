/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package performia.repo;

import performia.model.Tugas;

/**
 *
 * @author Zefa
 */
public interface ITugasRepo {
    void simpan(Tugas tugas);
    
    int totalRiwayat();
    
    Tugas getByIndex(int index);
}
