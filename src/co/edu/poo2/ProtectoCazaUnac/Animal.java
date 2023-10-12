package co.edu.poo2.ProtectoCazaUnac;

public  class Animal {
    private String nombreCientifico;
    private String nombre;
    private String conservacion;
    private String rolEcologico;
    private String habitad;
    private String descripsionAnimal;
    private String lugarAvistamiento;

    public Animal() {

    }

    public Animal(String nombreCientifico, String nombre, String conservacion, String rolEcologico, String habitad, String descripsionAnimal, String lugarAvistamiento) {
        this.nombreCientifico = nombreCientifico;
        this.nombre = nombre;
        this.conservacion = conservacion;
        this.rolEcologico = rolEcologico;
        this.habitad = habitad;
        this.descripsionAnimal = descripsionAnimal;
        this.lugarAvistamiento = lugarAvistamiento;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConservacion() {
        return conservacion;
    }

    public void setConservacion(String conservacion) {
        this.conservacion = conservacion;
    }

    public String getRolEcologico() {
        return rolEcologico;
    }

    public void setRolEcologico(String rolEcologico) {
        this.rolEcologico = rolEcologico;
    }

    public String getHabitad() {
        return habitad;
    }

    public void setHabitad(String habitad) {
        this.habitad = habitad;
    }

    public String getDescripsionAnimal() {
        return descripsionAnimal;
    }

    public void setDescripsionAnimal(String descripsionAnimal) {
        this.descripsionAnimal = descripsionAnimal;
    }

    public String getLugarAvistamiento() {
        return lugarAvistamiento;
    }

    public void setLugarAvistamiento(String lugarAvistamiento) {
        this.lugarAvistamiento = lugarAvistamiento;
    }
}
