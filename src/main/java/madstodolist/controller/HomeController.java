package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.controller.exception.UsuarioNotFoundException;
import madstodolist.model.Usuario;
import madstodolist.model.Equipo;
import madstodolist.service.EquipoService;
import madstodolist.service.EquipoServiceException;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EquipoService equipoService;

    @Autowired
    ManagerUserSession managerUserSession;

    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model){
        List<Usuario> todosusuarios = usuarioService.allUsuarios();
        model.addAttribute("usuarios",todosusuarios);
        return "usuarios";
    }

    @GetMapping("/usuarios/{id}")
    public String descripcionUsuario(@PathVariable(value = "id") Long idUsuario, Model model, HttpSession session){
        Usuario usuario = usuarioService.findById(idUsuario);
        if(usuario == null)
            throw new UsuarioNotFoundException();

        model.addAttribute("usuario",usuario);
        return "descripcionUsuario";
    }

    @GetMapping("/equipos")
    public String equipos(Model model){
        List<Equipo> todosequipos = equipoService.findAllOrderedByName();
        model.addAttribute("equipos",todosequipos);
        return "equipos";
    }

    @GetMapping("/equipos/{id}")
    public String miembrosEquipo(@PathVariable(value = "id") Long idEquipo,Model model, HttpSession session){
        Equipo equipo = equipoService.findById(idEquipo);
        if(equipo == null)
            throw new EquipoServiceException("No encotrado");
        model.addAttribute("equipo",equipo);
        return "miembrosEquipo";
    }
}