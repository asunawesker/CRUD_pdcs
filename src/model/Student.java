package model;

/**
 *
 * @author asunawesker
 */
public class Student {
    
    private int id;
    private String matricula;
    private String nombre;
    private String carrera;   

    public Student() {
    }

    public Student(int id, String matricula, String nombre, String carrera) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.carrera = carrera;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
        
}
