package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.controller.exception.UsuarioNotFoundException;
import madstodolist.model.Equipo;
import madstodolist.model.Usuario;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EquipoController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ManagerUserSession managerUserSession;

    @GetMapping("/usuarios/{id}/equipos")
    public String listadoEquiposUsuario(@PathVariable(value = "id")Long idUsuario, Model model, HttpSession session){
        managerUserSession.comprobarUsuarioLogeado(session,idUsuario);
        Usuario usuario = usuarioService.findById(idUsuario);
        if(usuario == null)
            throw new UsuarioNotFoundException();
        List<Equipo> equipos = (List<Equipo>) usuario.getEquipos();
        model.addAttribute("usuario",usuario);
        model.addAttribute("equipos",equipos);
        return "listaEquipos";
    }
}
