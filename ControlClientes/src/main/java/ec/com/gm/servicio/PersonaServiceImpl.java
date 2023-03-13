/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.com.gm.servicio;

import ec.com.gm.dao.IPersonaDao;
import ec.com.gm.domain.Persona;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private IPersonaDao iPersonaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) iPersonaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        iPersonaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        iPersonaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return iPersonaDao.findById(persona.getIdPersona()).orElse(null);
    }

}
