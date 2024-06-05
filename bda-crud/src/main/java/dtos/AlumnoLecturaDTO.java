package dtos;
/**
 * 
 * @author eduar
 */
public class AlumnoLecturaDTO {
    private int idAlumno;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private boolean activo;

    public AlumnoLecturaDTO(int idAlumno, String nombres, String apellidoPaterno, String apellidoMaterno, boolean activo) {
        this.idAlumno = idAlumno;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.activo = activo;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public boolean isActivo() {
        return activo;
    }
}
