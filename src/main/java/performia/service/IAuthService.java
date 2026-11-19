/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package performia.service;

/**
 *
 * @author Zefa
 */
public interface IAuthService {
    int cariManajerIndex(String email, String password);

    int cariKaryawanIndex(String email, String password);
}
