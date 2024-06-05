package negocio;

import dtos.AlumnoLecturaDTO;
import dtos.AlumnoTablaDTO;
import dtos.GuardarAlumnoDTO;
import dtos.EditarAlumnoDTO;
import java.util.List;

/**
 * 
 * @author eduar
 */
public interface IAlumnoNegocio {
    List<AlumnoTablaDTO> buscarAlumnosTabla(int limite, int pagina) throws NegocioException;
    AlumnoLecturaDTO obtenerPorId(int id) throws NegocioException;
    void insertar(GuardarAlumnoDTO dto) throws NegocioException;
    void editar(EditarAlumnoDTO dto) throws NegocioException;
}

