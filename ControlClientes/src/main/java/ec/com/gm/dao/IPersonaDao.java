package ec.com.gm.dao;

import ec.com.gm.domain.Persona;
import org.springframework.data.repository.CrudRepository;


public interface IPersonaDao extends CrudRepository<Persona, Long>{
    
}
