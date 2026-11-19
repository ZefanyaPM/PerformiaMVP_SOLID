/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package performia.repo;

import java.util.List;
import performia.user.Manajer;

/**
 *
 * @author Zefa
 */
public interface IManajerRepo {
    void tambahBatch(List<Manajer> batchManajer);
    
    void tambah(Manajer manajer);
    
    List<Manajer> getList();
    
    void bersihkan();
}
