package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Alumno;
import entidades.Curso;
import entidades.Matricula;

public class AcademiaDAOImplJDBC implements AcademiaDAO {
	// Cadena de conexión predeterminada
		private String URLConexion = new String("jdbc:mysql://tu_IP:3306/dbformacion?user=tu_usuario&password=tu_pwd");
		
		/*
		 * SQL
		 */
		private static final String FIND_ALL_ALUMNOS_SQL = "select id_alumno, nombre_alumno from alumnos";

	/*
		 * CONSTRUCTORES
		 */
		public AcademiaDAOImplJDBC() { }
		
		// Sobrecargamos el método por si queremos 
		// machacar la cadena de conexión 
		public AcademiaDAOImplJDBC(String URLConexion) {	
			this.URLConexion=URLConexion;
		}
		
		
		/*
		 * OPERACIONES GENERALES
		 */
		
		// Obtener la conexión
		private Connection getConnection() throws SQLException {
			return DriverManager.getConnection(URLConexion);
		}

		// Liberar la conexión
		private void releaseConnection(Connection con) {
			if (con != null) {
				try {
					con.close();
					con = null;
				} catch (SQLException e) {
					for (Throwable t : e) {
						System.err.println("Error: " + t);
					}
				}
			}
		}
		
		/*
		 * OPERACIONES ALUMNO
		 */
		@Override
		public Collection<Alumno> cargarAlumnos() {
			Collection<Alumno> alumnos = new ArrayList<Alumno>();
			Connection con = null;
			try {
				con = getConnection();
				PreparedStatement ps = con.prepareStatement(FIND_ALL_ALUMNOS_SQL);
				ResultSet rs = ps.executeQuery();			
				while (rs.next()) {
					int id= rs.getInt(1);				
					String nombre = 
						(rs.getString(2)!=null?rs.getString(2):"sin nombre");
					
					alumnos.add(new Alumno(id,nombre));
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				for (Throwable t : e) {
					System.err.println("Error: " + t);
				}
			} finally {
				releaseConnection(con);
			}
			return alumnos;
		}

		@Override
		public Alumno getAlumno(int idAlumno) {
			String sql = "SELECT * FROM alumnos WHERE id_alumno = ?";
			Alumno alumno = null;

			try (PreparedStatement ps = getConnection().prepareStatement(sql)){
				
				ps.setInt(1, idAlumno);
				
				ResultSet res = ps.executeQuery();
				
				alumno = new Alumno(res.getInt("id_alumno"), res.getString("nombre_alumno"));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return alumno;
		}

		@Override
		public int grabarAlumno(Alumno alumno) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int actualizarAlumno(Alumno alumno) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int borrarAlumno(int idAlumno) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Collection<Curso> cargarCursos() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Curso getCurso(int idCurso) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int grabarCurso(Curso curso) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int actualizarCurso(Curso curso) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int borrarCurso(int idCurso) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Collection<Matricula> cargarMatriculas() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getIdMatricula(int idAlumno, int idCurso) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Matricula getMatricula(long idMatricula) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int grabarMatricula(Matricula matricula) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int actualizarMatricula(Matricula matricula) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int borrarMatricula(long idMatricula) {
			// TODO Auto-generated method stub
			return 0;
		}
		

}
