package madstodolist.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "equipos")
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nombre;

    //Mirar como hacer lo de los usuarios
    //Mirar lo de usuarios para mostrar todos los usuarios o algo asi

    private Equipo(){}

    public Equipo(String nombre){
        this.nombre = nombre;
    }

    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public String getNombre(){return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        if(id != null && equipo.id != null)
            return Objects.equals(id,equipo.id);
        return nombre.equals(equipo.nombre);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nombre);
    }
}
