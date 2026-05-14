package pe.edu.upc.relaxup.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    private Long id;    // mismo valor que users.id

    @OneToOne
    @MapsId                       // usa el id de Users como PK
    @JoinColumn(name = "id_user") // nombre de la columna en la tabla usuario
    private Users user;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombres;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "direccion", nullable = false, length = 50)
    private String direccion;

    @Column(name = "celular", nullable = false)
    private int celular;

    // Constructor vacío (obligatorio para JPA)
    public Usuario() {}

    // Constructor con todos los campos (opcional)
    public Usuario(Long id, String nombres, String email, String direccion, int celular) {
        this.id = id;
        this.nombres = nombres;
        this.email = email;
        this.direccion = direccion;
        this.celular = celular;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }
}