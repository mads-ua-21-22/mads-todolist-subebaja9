package madstodolist.service;

import madstodolist.controller.exception.UsuarioNotFoundException;
import madstodolist.model.Equipo;
import madstodolist.model.EquipoRepository;
import madstodolist.model.Usuario;
import madstodolist.model.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EquipoService {

    Logger logger = LoggerFactory.getLogger(EquipoService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;
    private EquipoRepository equipoRepository;

    @Autowired
    public EquipoService(EquipoRepository equipoRepository){
        this.equipoRepository = equipoRepository;
    }

    @Transactional(readOnly = true)
    public List<Equipo> findAllOrderedByName(){
        List<Equipo> equipos = new ArrayList<>();
        equipos = (List<Equipo>) equipoRepository.findAll();
        Collections.sort(equipos, Comparator.comparing(Equipo::getNombre));
        return equipos;
    }

    @Transactional(readOnly = true)
    public Equipo findById(long equipoId) {
        logger.debug("Buscando equipo " + equipoId);
        return equipoRepository.findById(equipoId).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Usuario> usuariosEquipo(long idEquipo) {
        logger.debug("Devolviendo los usuarios del equipo " + idEquipo);
        Equipo equipo = equipoRepository.findById(idEquipo).orElse(null);
        if(equipo==null)
            throw new EquipoServiceException("Equipo no encontrado");
        List<Usuario> usuarios = new ArrayList<>(equipo.getUsuarios());
        return usuarios;
    }

    @Transactional
    public Equipo nuevoEquipoUsuario(Long idUsuario,String tituloEquipo){
        logger.debug("Añadiendo equipo " + tituloEquipo + " al usuario " + idUsuario);
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if(usuario==null)
            throw new EquipoServiceException("El usuario no se puede añadir");
        Equipo equipo = new Equipo(tituloEquipo);
        equipo.addUsuario(usuario);
        usuario.addEquipo(equipo);
        equipoRepository.save(equipo);
        return equipo;
    }

    @Transactional
    public Equipo existenteEquipoUsuario(Long idUsuario,String tituloEquipo){
        logger.debug("Añadiendo equipo " + tituloEquipo + " al usuario " + idUsuario);
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if(usuario==null)
            throw new EquipoServiceException("El usuario no se puede añadir");
        Equipo equipo = equipoRepository.findByNombre(tituloEquipo).orElse(null);
        if(equipo == null)
            throw new EquipoServiceException("El equipo no existe");
        if(equipo.getUsuarios().contains(usuario))
            throw new EquipoServiceException("El usuario ya existe en el equipo");
        equipo.addUsuario(usuario);
        usuario.addEquipo(equipo);
        equipoRepository.save(equipo);
        return equipo;
    }

    @Transactional
    public void borrarUsuarioEquipo(Long idUsuario, Long idEquipo) {
        logger.debug("Borrando relacion entre usuario " +idUsuario+" y equipo "+idEquipo);
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if(usuario == null)
            throw new UsuarioNotFoundException();
        Equipo equipo = equipoRepository.findById(idEquipo).orElse(null);
        if(equipo == null)
            throw new EquipoServiceException("Equipo no encontrado");
        equipo.deleteUsuario(usuario);
        usuario.deleteEquipo(equipo);
        equipoRepository.save(equipo);
    }
}
