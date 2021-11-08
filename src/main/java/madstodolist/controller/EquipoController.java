package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.controller.exception.UsuarioNotFoundException;
import madstodolist.model.Equipo;
import madstodolist.model.Usuario;
import madstodolist.service.EquipoService;
import madstodolist.service.EquipoServiceException;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
public class EquipoController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ManagerUserSession managerUserSession;
    @Autowired
    EquipoService equipoService;

    @GetMapping("/usuarios/{id}/equipos")
    public String listadoEquiposUsuario(@PathVariable(value = "id")Long idUsuario, Model model, HttpSession session){
        managerUserSession.comprobarUsuarioLogeado(session,idUsuario);
        Usuario usuario = usuarioService.findById(idUsuario);
        if(usuario == null)
            throw new UsuarioNotFoundException();
        Set<Equipo> equipos = usuario.getEquipos();
        model.addAttribute("usuario",usuario);
        model.addAttribute("equipos",equipos);
        return "listaEquipos";
    }

    @GetMapping("/usuarios/{id}/equipos/nuevo")
    public String formNuevoEquipo(@PathVariable(value = "id") Long idUsuario,
                                  @ModelAttribute EquipoData equipoData, Model model,
                                  HttpSession session){
        managerUserSession.comprobarUsuarioLogeado(session,idUsuario);

        Usuario usuario = usuarioService.findById(idUsuario);
        if (usuario == null)
            throw new UsuarioNotFoundException();
        model.addAttribute("usuario",usuario);
        return "formNuevoEquipo";
    }

    @PostMapping("/usuarios/{id}/equipos/nuevo")
    public String nuevoEquipo(@PathVariable(value="id") Long idUsuario, @ModelAttribute EquipoData equipoData,
                              Model model, RedirectAttributes flash,
                              HttpSession session){
        managerUserSession.comprobarUsuarioLogeado(session,idUsuario);

        Usuario usuario = usuarioService.findById(idUsuario);
        if(usuario == null)
            throw new UsuarioNotFoundException();
        equipoService.nuevoEquipoUsuario(idUsuario,equipoData.getTitulo());
        flash.addFlashAttribute("mensaje","Equipo creado correctamente");
        return "redirect:/usuarios/"+idUsuario+"/equipos";
    }

    @GetMapping("/usuarios/{id}/equipos/existente")
    public String formExistenteEquipo(@PathVariable(value = "id") Long idUsuario,
                                      @ModelAttribute EquipoData equipoData, Model model,
                                      HttpSession session){
        managerUserSession.comprobarUsuarioLogeado(session,idUsuario);
        Usuario usuario = usuarioService.findById(idUsuario);
        if(usuario==null)
            throw new UsuarioNotFoundException();
        List<Equipo> equipos = equipoService.findAllOrderedByName();
        model.addAttribute("usuario",usuario);
        model.addAttribute("equipos",equipos);
        return "formExistenteEquipo";
    }

    @PostMapping("/usuarios/{id}/equipos/existentes")
    public String existenteEquipo(@PathVariable(value="id") Long idUsuario, @ModelAttribute EquipoData equipoData,
                                  Model model, RedirectAttributes flash,
                                  HttpSession session){
        managerUserSession.comprobarUsuarioLogeado(session,idUsuario);

        Usuario usuario = usuarioService.findById(idUsuario);
        if(usuario == null)
            throw new UsuarioNotFoundException();
        equipoService.existenteEquipoUsuario(idUsuario,equipoData.getTitulo());
        flash.addFlashAttribute("mensaje","Equipo creado correctamente");
        return "redirect:/usuarios/"+idUsuario+"/equipos";
    }

    @DeleteMapping("/usuarios/{id}/equipos/{id_equipo}")
    @ResponseBody
    public String borrarUsuarioEquipo(@PathVariable(value="id") Long idUsuario,@PathVariable(value = "id_equipo") Long idEquipo,RedirectAttributes flahs,HttpSession session){
        Usuario usuario = usuarioService.findById(idUsuario);
        if(usuario==null)
            throw new UsuarioNotFoundException();
        Equipo equipo = equipoService.findById(idEquipo);
        if(equipo == null)
            throw new EquipoServiceException("Equipo no encontrado");
        managerUserSession.comprobarUsuarioLogeado(session,idUsuario);
        equipoService.borrarUsuarioEquipo(idUsuario,idEquipo);
        return "";
    }
}
