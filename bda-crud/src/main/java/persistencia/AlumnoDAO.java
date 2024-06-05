package persistencia;

import entidad.AlumnoEntidad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author eduar
 */
public class AlumnoDAO implements IAlumnoDAO {
    private IConexionBD conexionBD;

    public AlumnoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<AlumnoEntidad> buscarAlumnosTabla(int limite, int pagina) throws PersistenciaException {
        try {
            List<AlumnoEntidad> alumnosLista = new ArrayList<>();

            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idAlumno, nombres, apellidoPaterno, apellidoMaterno, eliminado, activo FROM alumnos LIMIT " + limite + " OFFSET " + ((pagina - 1) * limite);
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next()) {
                AlumnoEntidad alumno = this.convertirAEntidad(resultado);
                alumnosLista.add(alumno);
            }
            conexion.close();
            return alumnosLista;
        } catch (SQLException ex) {
            // hacer uso de Logger
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    @Override
    public AlumnoEntidad obtenerPorId(int id) throws PersistenciaException {
        try {
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idAlumno, nombres, apellidoPaterno, apellidoMaterno, eliminado, activo FROM alumnos WHERE idAlumno = " + id;
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            if (resultado.next()) {
                AlumnoEntidad alumno = this.convertirAEntidad(resultado);
                conexion.close();
                return alumno;
            } else {
                conexion.close();
                throw new PersistenciaException("No se encontró el alumno con el id " + id);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    @Override
    public void insertar(AlumnoEntidad alumno) throws PersistenciaException {
        try {
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "INSERT INTO alumnos (nombres, apellidoPaterno, apellidoMaterno, eliminado, activo) VALUES ('"
                    + alumno.getNombres() + "', '"
                    + alumno.getApellidoPaterno() + "', '"
                    + alumno.getApellidoMaterno() + "', "
                    + alumno.isEliminado() + ", "
                    + alumno.isActivo() + ")";
            Statement comandoSQL = conexion.createStatement();
            comandoSQL.executeUpdate(codigoSQL);
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al insertar el alumno, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    @Override
    public void editar(AlumnoEntidad alumno) throws PersistenciaException {
        try {
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "UPDATE alumnos SET "
                    + "nombres = '" + alumno.getNombres() + "', "
                    + "apellidoPaterno = '" + alumno.getApellidoPaterno() + "', "
                    + "apellidoMaterno = '" + alumno.getApellidoMaterno() + "', "
                    + "eliminado = " + alumno.isEliminado() + ", "
                    + "activo = " + alumno.isActivo() + " "
                    + "WHERE idAlumno = " + alumno.getIdAlumno();
            Statement comandoSQL = conexion.createStatement();
            comandoSQL.executeUpdate(codigoSQL);
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al editar el alumno, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    private AlumnoEntidad convertirAEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("idAlumno");
        String nombre = resultado.getString("nombres");
        String paterno = resultado.getString("apellidoPaterno");
        String materno = resultado.getString("apellidoMaterno");
        boolean eliminado = resultado.getBoolean("eliminado");
        boolean activo = resultado.getBoolean("activo");
        return new AlumnoEntidad(id, nombre, paterno, materno, eliminado, activo);
    }
}
