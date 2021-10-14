package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.controller.exception.UsuarioNotFoundException;
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
public class HomeController {

    @Autowired
    UsuarioService usuarioService;

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
}