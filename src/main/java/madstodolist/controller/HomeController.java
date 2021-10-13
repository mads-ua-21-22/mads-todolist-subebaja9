package madstodolist.controller;

import madstodolist.model.Usuario;
import madstodolist.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    UsuarioService usuarioService;

    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model){
        Iterable<Usuario> todosusuarios = usuarioService.getAll();
        model.addAttribute("usuarios",todosusuarios);
        return "usuarios";
    }
}