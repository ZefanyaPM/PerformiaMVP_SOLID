/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package performia.repo;

import java.util.ArrayList;
import java.util.List;
import performia.user.Manajer;

/**
 *
 * @author Zefa
 */
public class ManajerArrayList implements IManajerRepo{
    private List<Manajer> listManajer = new ArrayList<>();
    
    @Override
    public void tambahBatch(List<Manajer> batchManajer) {
        listManajer.addAll(batchManajer);
    }

    @Override
    public void tambah(Manajer manajer) {
        listManajer.add(manajer);
    }
    
    @Override
    public List<Manajer> getList(){
        return listManajer;
    }

    @Override
    public void bersihkan() {
        listManajer.clear();
    }
    
}
