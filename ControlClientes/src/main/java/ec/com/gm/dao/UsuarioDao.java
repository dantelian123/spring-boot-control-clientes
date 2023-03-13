/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.com.gm.dao;

import ec.com.gm.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Municipio de Gye
 */
public interface UsuarioDao extends JpaRepository<Usuario,  Long> {
    Usuario findByUsername(String username);
}
