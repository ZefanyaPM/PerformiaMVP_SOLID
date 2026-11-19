/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package performia.repo;

import performia.model.Tugas;

/**
 *
 * @author Zefa
 */
public interface IRiwayatTugasRepo {
    void simpan(Tugas tugas);
    
    int totalRiwayat();
    
    Tugas getByIndex(int index);
}
