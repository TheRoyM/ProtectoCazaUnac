package co.edu.poo2.ProtectoCazaUnac;public class Usuario {    private String nombreCompleto;    private String correoElectronico;    private String user;    private String password;    public Usuario() {    }    public Usuario(String nombreCompleto, String correoElectronico, String user, String password) {        this.nombreCompleto = nombreCompleto;        this.correoElectronico = correoElectronico;        this.user = user;        this.password = password;    }    public String getNombreCompleto() {        return nombreCompleto;    }    public void setNombreCompleto(String nombreCompleto) {        this.nombreCompleto = nombreCompleto;    }    public String getCorreoElectronico() {        return correoElectronico;    }    public void setCorreoElectronico(String correoElectronico) {        this.correoElectronico = correoElectronico;    }    public String getUser() {        return user;    }    public void setUser(String user) {        this.user = user;    }    public String getPassword() {        return password;    }    public void setPassword(String password) {        this.password = password;    }}