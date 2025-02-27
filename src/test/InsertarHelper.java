package test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import dao.AcademiaDAO;
import dao.AcademiaDAOImplJDBC;
import entidades.Alumno;
import entidades.Curso;
import entidades.Matricula;

public class InsertarHelper {
	
	private AcademiaDAO dao=null;
	
	// Constructor
	public InsertarHelper() {
		System.out.println("Creando el DAO...");
		dao=new AcademiaDAOImplJDBC();
	}
	
	/*
	 * Alumnos
	 */
	private void insertarAlumno(int id, String nombre) {
		System.out.println("\nCreando un alumno...");
		if (dao.getAlumno(id) == null) {
			Alumno alumno=new Alumno(id,nombre);
			
			System.out.println("Grabando el nuevo alumno...");
			if (dao.grabarAlumno(alumno)==1) {
				System.out.print("Se ha grabado el alumno");
			} else {
				System.out.print("Error al grabar el alumno");
			}
		} else {
			System.out.println("Ya existe un alumno con id " + id + " en la base de datos");
		}
		
		
	}
	
	private void modificarAlumno(int id, String nombre) {
		
		// Recuperamos al alumno a partir de su id
		Alumno alumno=dao.getAlumno(id);
		
		System.out.println("\nModificando el nombre del alumno con id: "+id+" y nombre: "+alumno.getNombreAlumno());
		alumno.setNombreAlumno(nombre);
		
		if (dao.actualizarAlumno(alumno)==1) {
			System.out.print("Se ha modificado el alumno con id: "+id);
		} else {
			System.out.print("Error al modificar el alumno con id: "+id);
		}
	}
	
	/*
	 * Cursos
	 */
	
	private void insertarCurso(int id, String nombre) {
		System.out.println("\nCreando un curso...");
		
		if (dao.getCurso(id) == null) {
			Curso curso=new Curso(id,nombre);
			
			System.out.println("Grabando el nuevo curso...");
			if (dao.grabarCurso(curso)==1) {
				System.out.print("Se ha grabado el curso");
			} else {
				System.out.print("Error al grabar el curso");
			}
		} else {
			System.out.println("Ya existe un curso con id " + id + " en la base de datos");
		}
		
	}
	
	
	private void modificarCurso(int id, String nombre) {
		// Recuperamos al curso a partir de su id
		Curso curso = dao.getCurso(id);
		
		System.out.println("\nModificando el nombre del curso con id: "+id+" y nombre: "+ curso.getNombreCurso());
		curso.setNombreCurso(nombre);
		
		if (dao.actualizarCurso(curso)==1) {
			System.out.print("Se ha modificado el curso con id: "+id);
		} else {
			System.out.print("Error al modificar el curso con id: "+id);
		}
	}
	
	/*
	 * Matriculas
	 */
			
	private void insertarMatricula(int id_alumno, int id_curso) {
		System.out.println("\nCreando la matricula...");
		
		long id_matricula = dao.getIdMatricula(id_alumno, id_curso);
		
		if(dao.getMatricula(id_matricula) == null) {
			Matricula matricula = new Matricula(id_alumno, id_curso, LocalDate.now());
			
			System.out.println("Grabando la nueva matricula...");
			if (dao.grabarMatricula(matricula)==1) {
				System.out.print("Se ha grabado la matricula");
			} else {
				System.out.print("Error al grabar la matricula");
			}
		} else {
			System.out.println("Ya existe una matricula con id con id_matricula " + id_matricula +", id_alumno: "+ id_alumno +" y id_curso: "+ id_curso);
		}
		
	}
	
	private void modificarMatricula(int id_alumno, int id_curso, java.util.Date fecha) {
		// Recuperamos al curso a partir de su id
		Long id_matricula = dao.getIdMatricula(id_alumno, id_curso);
		Matricula matricula = dao.getMatricula(id_matricula);
		
		System.out.println("\nModificando la fecha de la matricula con id_matricula" + id_matricula +", id_alumno: "+ id_alumno +" y id_curso: "+ id_curso);
		matricula.setFecha(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());;
		
		if (dao.actualizarMatricula(matricula)==1) {
			System.out.print("Se ha modificado la matricula con id: "+ id_matricula);
		} else {
			System.out.print("Error al modificar la matricula con id: "+ id_matricula);
		}				
	}
	
	private void showAllData() {
		showData(dao.cargarAlumnos(),"Alumnos");
		showData(dao.cargarCursos(),"Cursos");
		showData(dao.cargarMatriculas(),"Matriculas");		
	}
	
	private void showData(Collection<?> coleccion, String entidad) {
		
		System.out.println("\nMostrando..."+entidad);
		
		for (Object obj:coleccion) 
			System.out.println(obj);		
	}
	
	public static void main(String[] args) {
		
		InsertarHelper programa=new InsertarHelper();
				
		/*
		 * Insertar alumnos
		 */
		
		programa.insertarAlumno(1000,"Daniel");
		programa.insertarAlumno(1001,"Francisco");
				
		// Cambiarle el nombre al primer alumno creado
		programa.modificarAlumno(1000, "Ezequiel");
		
		/*
		 * Insertar cursos
		 */
		programa.insertarCurso(500, "Java");
		programa.insertarCurso(501, ".NET");
		
		// Modificar el curso creado		
		programa.modificarCurso(500, "Java avanzado");
		
			
		/*
		 * Insertar matriculas
		 */		
		programa.insertarMatricula(1000, 500);
		programa.insertarMatricula(1000, 501);
		programa.insertarMatricula(1001, 500);		
		
		/*
		 * Modificar fecha de la segunda matricula
		 */		
		Calendar fecha=GregorianCalendar.getInstance();
		fecha.set(Calendar.MONTH, 11);
		programa.modificarMatricula(1001, 500, fecha.getTime());
						
		/*
		 * Mostrar lo que hemos grabado
		 * 
		 */
		programa.showAllData();
				
		System.out.println("\nfin del programa.");
	}

}
