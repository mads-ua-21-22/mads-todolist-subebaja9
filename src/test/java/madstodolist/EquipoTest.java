package madstodolist;

import madstodolist.model.Equipo;
import madstodolist.model.EquipoRepository;
import madstodolist.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EquipoTest {
    /*@Autowired
    private EquipoRepository equiposRepository;
    Usuario usuario1 = new Usuario("juan.gutierrez@gmail.com");
    Usuario usuario2 = new Usuario("fran.gutierrez@gmail.com");*/

    @Test
    public void crearEquipo() throws Exception{
        //GIVEN
        Equipo equipo = new Equipo("Equipo1");
        //THEN
        assertThat(equipo.getNombre()).isEqualTo("Equipo1");
    }
/*
    @Test
    @Transactional
    public void creaEquipoBaseDatos() throws Exception{
        //GIVEN
        Equipo equipo = new Equipo("Equipo1");

        //WHEN
        equiposRepository.save(equipo);

        //THEN
        assertThat(equipo.getId()).isNotNull();
        assertThat(equipo.getNombre()).isEqualTo("Equipo1");
    }

    @Test
    @Transactional(readOnly = true)
    public void buscarEquipoEnBaseDatos() throws Exception{
        //GIVEN
        //En el applicacion.properties se cargan los datos de prueba del fichero datos-test.sql

        //WHEN
        Equipo equipo = equiposRepository.findById(1L).orElse(null);

        //THEN
        assertThat(equipo).isNotNull();
        assertThat(equipo.getId()).isEqualTo(1L);
        assertThat(equipo.getNombre()).isEqualTo("EquipoDataBase");
    }

    @Test
    @Transactional(readOnly = true)
    public void buscarEquipoPorNombre(){
        //GIVEN
        //Datos cargados de datos-test.sql

        //WHEN
        Equipo equipo = equiposRepository.findByNombre("EquipoDataBase").orElse(null);

        //THEN
        assertThat(equipo).isNotNull();
    }

    @Test
    public void comprobarIgualdadSinId(){
        //GIVEN
        Equipo equipo1 = new Equipo("Equipo hardcore");
        Equipo equipo2 = new Equipo("Equipo hardcore");
        Equipo equipo3 = new Equipo("Esquipo lofi");

        //WHEN

        //THEN
        assertThat(equipo1).isEqualTo(equipo1);
        assertThat(equipo1).isEqualTo(equipo2);
        assertThat(equipo1).isNotEqualTo(equipo3);
    }

    @Test
    public void comprobarIgualdadConId(){
        //GIVEN
        Equipo equipo1 = new Equipo("Equipo hardcore");
        equipo1.setId(1L);
        Equipo equipo2 = new Equipo("Equipo hardcore");
        equipo2.setId(1L);
        Equipo equipo3 = new Equipo("Esquipo lofi");
        equipo3.setId(2L);

        //WHEN

        //THEN
        assertThat(equipo1).isEqualTo(equipo2);
        assertThat(equipo1).isNotEqualTo(equipo3);
    }*/
}
