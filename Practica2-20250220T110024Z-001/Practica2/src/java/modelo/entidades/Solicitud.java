package modelo.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Solicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length = 20, nullable = false)
    private String email;
    
    @Column(length = 30, nullable = false)
    private String password;
    
    @Column(length = 20, nullable = false)
    private String nombre;
    
    @Column(length = 20, nullable = false)
    private String apellidos;
    
    @Column(nullable = false)
    private boolean aprobado = false;
    
    public Solicitud() {}
    
    public Solicitud(String email, String password, String nombre, String apellidos) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.aprobado = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; 
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public boolean isAprobado() {
        return aprobado;
    }
    
    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }
}
