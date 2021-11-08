package madstodolist.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "equipos")
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "equipo_usuario",
                joinColumns = {@JoinColumn(name = "fk_equipo")},
                inverseJoinColumns = {@JoinColumn(name = "fk_usuario")})
    Set<Usuario> usuarios = new HashSet<>();

    private Equipo() {}

    public Equipo(String nombre) {this.nombre=nombre;}

    public String getNombre(){return nombre;}

    public void setNombre(String nombre){this.nombre=nombre;}

    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public Set<Usuario> getUsuarios(){return usuarios;}

    public void getUsuarios(Set<Usuario> usuarios) {this.usuarios = usuarios;}

    public void addUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void deleteUsuario(Usuario usuario) { usuarios.remove(usuario); }

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
