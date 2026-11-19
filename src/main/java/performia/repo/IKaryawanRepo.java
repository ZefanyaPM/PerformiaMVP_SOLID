/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package performia.repo;

import performia.user.Karyawan;
import java.util.List;
/**
 *
 * @author Zefa
 */
public interface IKaryawanRepo {
    void tambahBatch(List<Karyawan> batchKaryawan);
    
    void tambah(Karyawan karyawan);
    
    List<Karyawan> getList();
    
    void bersihkan();
}
