package persistencia;

import entidad.AlumnoEntidad;
import java.util.List;

/**
 * 
 * @author eduar
 */
public interface IAlumnoDAO {
    List<AlumnoEntidad> buscarAlumnosTabla(int limite, int pagina) throws PersistenciaException;
    AlumnoEntidad obtenerPorId(int id) throws PersistenciaException;
    void insertar(AlumnoEntidad alumno) throws PersistenciaException;
    void editar(AlumnoEntidad alumno) throws PersistenciaException;
}
