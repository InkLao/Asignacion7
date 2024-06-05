package negocio;

import dtos.AlumnoLecturaDTO;
import dtos.AlumnoTablaDTO;
import dtos.GuardarAlumnoDTO;
import dtos.EditarAlumnoDTO;
import entidad.AlumnoEntidad;
import persistencia.IAlumnoDAO;
import persistencia.PersistenciaException;

import java.util.ArrayList;
import java.util.List;

public class AlumnoNegocio implements IAlumnoNegocio {
    private IAlumnoDAO alumnoDAO;

    public AlumnoNegocio(IAlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }

    @Override
    public List<AlumnoTablaDTO> buscarAlumnosTabla(int limite, int pagina) throws NegocioException {
        try {
            List<AlumnoEntidad> alumnos = this.alumnoDAO.buscarAlumnosTabla(limite, pagina);
            return this.convertirAlumnoTablaDTO(alumnos);
        } catch (PersistenciaException ex) {
            // hacer uso de Logger
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public AlumnoLecturaDTO obtenerPorId(int id) throws NegocioException {
        try {
            AlumnoEntidad alumno = this.alumnoDAO.obtenerPorId(id);
            return new AlumnoLecturaDTO(alumno.getIdAlumno(), alumno.getNombres(), alumno.getApellidoPaterno(), alumno.getApellidoMaterno(), alumno.isActivo());
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public void insertar(GuardarAlumnoDTO dto) throws NegocioException {
        try {
            AlumnoEntidad alumno = new AlumnoEntidad();
            alumno.setNombres(dto.getNombres());
            alumno.setApellidoPaterno(dto.getApellidoPaterno());
            alumno.setApellidoMaterno(dto.getApellidoMaterno());
            this.alumnoDAO.insertar(alumno);
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public void editar(EditarAlumnoDTO dto) throws NegocioException {
        try {
            AlumnoEntidad alumno = new AlumnoEntidad();
            alumno.setIdAlumno(dto.getIdAlumno());
            alumno.setNombres(dto.getNombres());
            alumno.setApellidoPaterno(dto.getApellidoPaterno());
            alumno.setApellidoMaterno(dto.getApellidoMaterno());
            alumno.setActivo(dto.isActivo());
            this.alumnoDAO.editar(alumno);
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    private List<AlumnoTablaDTO> convertirAlumnoTablaDTO(List<AlumnoEntidad> alumnos) throws NegocioException {
        if (alumnos == null) {
            throw new NegocioException("No se pudieron obtener los alumnos");
        }

        List<AlumnoTablaDTO> alumnosDTO = new ArrayList<>();
        for (AlumnoEntidad alumno : alumnos) {
            AlumnoTablaDTO dto = new AlumnoTablaDTO();
            dto.setIdAlumno(alumno.getIdAlumno());
            dto.setNombres(alumno.getNombres());
            dto.setApellidoPaterno(alumno.getApellidoPaterno());
            dto.setApellidoMaterno(alumno.getApellidoMaterno());
            dto.setEstatus(alumno.isActivo() ? "Activo" : "Inactivo");
            alumnosDTO.add(dto);
        }
        return alumnosDTO;
    }
}
