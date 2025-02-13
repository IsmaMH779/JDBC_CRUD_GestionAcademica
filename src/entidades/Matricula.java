package entidades;

import java.io.Serializable;
import java.time.LocalDate;

public class Matricula implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idMatricula;
	private int alumnoID;
	private int cursoID;
	private LocalDate fecha;
	
	public Matricula() {
	}
	
	public Matricula(int idMatricula, int alumnoID, int cursoID, LocalDate fecha) {
		this.idMatricula = idMatricula;
		this.alumnoID = alumnoID;
		this.cursoID = cursoID;
		this.fecha = fecha;
	}

	public int getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	public int getAlumnoID() {
		return alumnoID;
	}

	public void setAlumnoID(int alumnoID) {
		this.alumnoID = alumnoID;
	}

	public int getCursoID() {
		return cursoID;
	}

	public void setCursoID(int cursoID) {
		this.cursoID = cursoID;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return this.idMatricula + " - ";
	}
		
	
}
