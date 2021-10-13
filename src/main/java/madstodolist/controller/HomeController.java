package madstodolist.controller;

import madstodolist.model.Usuario;
import madstodolist.model.UsuarioRepository;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model){
        List<Usuario> todosusuarios = usuarioService.allUgit suarios();
        model.addAttribute("usuarios",todosusuarios);
        return "usuarios";
    }
}