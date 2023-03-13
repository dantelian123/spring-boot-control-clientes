package ec.com.gm.web;

import ec.com.gm.domain.Persona;
import ec.com.gm.servicio.PersonaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//Contorlador mvc
@Controller
//Para mandar informacion al log
@Slf4j
public class ControladorInicio {

    @Autowired
    private PersonaService personaService;

    //Para indicar al navegador que este metodo debe eecutarse debemos agregarle un path  
    @GetMapping("/")
    public String inicio(Model model,@AuthenticationPrincipal User user) {
        var personas = personaService.listarPersonas();
        model.addAttribute("personas", personas);
        log.info("Ejecutando el controlador Spring MVC");
        log.info("Usuario que hizo login" + user);
        var saldoTotal=0D;
        for(var p: personas){
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        //regresa el nombre de la pagina que se despliega en el navegador
        return "index";
    }
    
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona",persona);
        return "modificar";
    }
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
