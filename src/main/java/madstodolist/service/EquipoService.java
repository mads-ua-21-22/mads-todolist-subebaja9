package madstodolist.service;

import madstodolist.model.Equipo;
import madstodolist.model.EquipoRepository;
import madstodolist.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EquipoService {

    Logger logger = LoggerFactory.getLogger(EquipoService.class);

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
}
