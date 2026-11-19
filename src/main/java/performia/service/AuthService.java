/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package performia.service;

import java.util.List;

import performia.app.PerformiaData;
import performia.user.Pengguna;

/**
 *
 * @author Zefa
 */
public class AuthService implements IAuthService{
    private final PerformiaData data;

    public AuthService(PerformiaData data) {
        this.data = data;
    }

    @Override
    public int cariManajerIndex(String email, String password) {
        return findUser(data.getListManajer(), email, password);
    }

    @Override
    public int cariKaryawanIndex(String email, String password) {
        return findUser(data.getListKaryawan(), email, password);
    }

    private static int findUser(
            List<? extends Pengguna> listPengguna,
            String email,
            String password
    ) {
        for (int index = 0; index < listPengguna.size(); index++) {
            Pengguna pengguna = listPengguna.get(index);
            if (pengguna.getEmail().equals(email) && pengguna.getPassword().equals(password)) {
                return index;
            }
        }
        return -1;
    }
}
