package dtos;

/**
 * 
 * @author eduar
 */
public class GuardarAlumnoDTO {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public GuardarAlumnoDTO(String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
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
}
