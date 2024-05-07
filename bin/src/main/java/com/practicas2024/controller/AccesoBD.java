package com.practicas2024.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import pojos.College;

/**
 * Clase que gestiona el acceso a los datos
 */
public class AccesoBD {

	private static final String HOST = "modulo2-mysqlserverdb8.mysql.database.azure.com";
	private static final Integer PORT = 3306;
	private static final String DB_NAME = "universidades_db";
	private static final String USER = "mysqladmin";
	private static final String PASSWD = "Adminbd8.pass";

	/**
	 * Crea el objeto y carga el driver de MySQL
	 * 
	 * @throws ExcepcionModulo2 Se puede producir cuando surge un error inesperado
	 *                          al cargar el driver de MySQL
	 */
	public AccesoBD() throws ExcepcionModulo2 {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		}

		catch (Exception ex) {
			ExcepcionModulo2 e = new ExcepcionModulo2();
			e.setMensajePersonalizado("Ha surgido un error inesperado");
			e.setMensajeError(ex.getMessage());
			e.setMetodoError("Constructor de la clase AccesoBD");
			throw e;
		}
	}

	/**
	 * Lee los datos de toda la tabla
	 * 
	 * @return Lista con los datos de las universidades
	 * @throws ExcepcionModulo2
	 */
	public ArrayList<DatosUniversidad> leerUniversidades() throws ExcepcionModulo2 {
	    ArrayList<DatosUniversidad> universidades;
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useSSL=true", USER, PASSWD);
	        Statement stmt = conn.createStatement();
	        String query = "select * from universidades;";
	        ResultSet rs = stmt.executeQuery(query);

	        universidades = new ArrayList<>();

	        while (rs.next()) {
	            DatosUniversidad universidad = new DatosUniversidad();
	            universidad.setUid(rs.getInt("uid"));
	            universidad.setNombre(rs.getString("nombre"));
	            universidad.setPaginaWeb(rs.getString("pagina_web"));
	            universidad.setPais(rs.getString("pais"));
	            universidad.setProvinciaEstado(rs.getString("provincia_estado"));
	            universidad.setFechaGuardado(rs.getDate("fecha_guardado"));
	            universidades.add(universidad);
	        }

	    } catch (Exception ex) {
	        ExcepcionModulo2 e = new ExcepcionModulo2();
	        e.setMensajePersonalizado("Ha surgido un error inesperado");
	        e.setMensajeError(ex.getMessage());
	        e.setMetodoError("leerUniversidades()");
	        throw e;
	    }

	    return universidades;
	}
	
	/**
	 * Lee los datos de toda la tabla usuarios
	 * 
	 * @return Lista con los datos de los usuarios
	 */
	public ArrayList<DatosUsuario> leerUsuarios() throws ExcepcionModulo2 {

		ArrayList<DatosUsuario> usuarios = new ArrayList<AccesoBD.DatosUsuario>();
		try {
			Connection conn = DriverManager
					.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useSSL=true", USER, PASSWD);
			Statement stmt = conn.createStatement();
			String query = "select * from usuarios;";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				DatosUsuario usuario = new DatosUsuario();           
				usuario.setUid(rs.getInt("uid"));
				usuario.setLogin(rs.getString("login"));
				usuario.setPassword(rs.getString("password"));
				usuarios.add(usuario);

			}

		} catch (Exception ex) {

			ExcepcionModulo2 e = new ExcepcionModulo2();
			e.setMensajePersonalizado("Ha surgido un error inesperado");
			e.setMensajeError(ex.getMessage());
			e.setMetodoError("leerUsuarios()");
			throw e;
			
		}

		return usuarios;
	}

	/**
	 * Elimina una universidad de la base de datos.
	 *
	 * @param uid El identificador único de la universidad a eliminar.
	 * @return True si la universidad se eliminó correctamente, false si no se encontró la universidad con el UID especificado.
	 * @throws ExcepcionModulo2 Si ocurre un error durante la eliminación.
	 */

	public void eliminarUniversidad(String uid) throws ExcepcionModulo2 {
	    try {
	        // Convertir uid de String a int
	        int id = Integer.parseInt(uid);

	        Connection conn = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useSSL=true", USER, PASSWD);
	        String sql = "DELETE FROM universidades WHERE uid = ?";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, id);
	        int rowCount = statement.executeUpdate();
	        statement.close();
	        conn.close();
	    } catch (NumberFormatException ex) {
	        // Manejar el error si uid no es un entero válido
	        ExcepcionModulo2 e = new ExcepcionModulo2("El ID proporcionado no es un número válido", ex.getMessage(), "Número inválido", "eliminarUniversidad()");
	        throw e;
	    } catch (SQLException ex) {
	        // Manejar el error de SQL
	        ExcepcionModulo2 e = new ExcepcionModulo2("", ex.getMessage(), String.valueOf(ex.getErrorCode()), "eliminarUniversidad()");
	        throw e;
	    }
	}


	/**
	 * Muestra por consola las tablas de la base de datos
	 * 
	 * @throws ExcepcionModulo2
	 */
	public void test() throws ExcepcionModulo2 {

		try {
			Connection conn = DriverManager
					.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useSSL=true", USER, PASSWD);
			Statement stmt = conn.createStatement();
			String query = "show tables;";
			ResultSet rs = stmt.executeQuery(query);
			int i = 1;
			while (rs.next()) {
				System.out.println(rs.getString(i));
				i++;
			}

		} catch (Exception ex) {

			ExcepcionModulo2 e = new ExcepcionModulo2();
			e.setMensajePersonalizado("Ha surgido un error inesperado");
			e.setMensajeError(ex.getMessage());
			e.setMetodoError("test()");
			throw e;
		}
	}

	/**
	 * Metodo que inserta un College a la base de datos remota
	 * 
	 * @param c El College a insertar
	 * @return Devuelve 1 si se ejecuto con exito, devuelve 0 si hay algun error
	 * @throws ExcepcionModulo2 Lanza una excepcion si falla la conexion con la bd o
	 *                          la ejecucion de la insert
	 */
	public int insertCollege(College c) throws ExcepcionModulo2 {
		int ra = 0;
		String sql = "INSERT INTO UNIVERSIDADES(nombre, pagina_web, pais, provincia_estado) VALUES (?,?,?,?)";
		try {
			Connection con = DriverManager
					.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useSSL=true", USER, PASSWD);
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, c.getName());
			ps.setString(2, c.getWebpage());
			ps.setString(3, c.getCountry());
			ps.setString(4, c.getState());

			ra = ps.executeUpdate();
			ps.close();
			con.close();

		} catch (SQLException ex) {
			ExcepcionModulo2 e = new ExcepcionModulo2("", ex.getMessage(), String.valueOf(ex.getErrorCode()), sql);
			throw e;
		}
		return ra;
	}
	
	/**
	 * Adds a new college to the database.
	 *
	 * @param collegeName  The name of the college.
	 * @param countryName  The name of the country where the college is located.
	 * @param provinceName The name of the province or state where the college is located.
	 * @param webPage      The web page URL of the college.
	 * @return The number of rows affected (1 if successful, 0 otherwise).
	 * @throws ExcepcionModulo2 If an unexpected error occurs during the database operation.
	 */
	public Integer addCollege(String collegeName,String countryName, String provinceName, String webPage) throws ExcepcionModulo2 {
		Integer rowCount = 0;
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useSSL=true", USER, PASSWD);
			Statement stmt = conn.createStatement() ;
			String query = "INSERT INTO universidades(nombre,pagina_web,pais,provincia_estado) VALUES(?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, collegeName);
			statement.setString(2, webPage);
			statement.setString(3, countryName);
			statement.setString(4, provinceName);
			
			rowCount = statement.executeUpdate();
			
			statement.close();
			conn.close();
		} catch (Exception ex) {
			ExcepcionModulo2 e = new ExcepcionModulo2();
			e.setMensajePersonalizado("Ha surgido un error inesperado");
			e.setMensajeError(ex.getMessage());
			e.setMetodoError("addCollege()");
			throw e;
		}
			
		return rowCount;
	}

	/**
	 * Objeto que guarda todos los datos de la universidad
	 */
	public class DatosUniversidad {

		private Integer uid;
		private String nombre;
		private String paginaWeb;
		private String pais;
		private String provinciaEstado;
		private java.util.Date fechaGuardado;

		/**
		 * Constructor vacio
		 */
		public DatosUniversidad() {
			this.uid = 0;
		}

		/**
		 * Constructor completo de todos los parametros
		 * 
		 * @param uid
		 * @param nombre
		 * @param paginaWeb
		 * @param pais
		 * @param provinciaEstado
		 * @param fechaGuardado
		 */
		public DatosUniversidad(Integer uid, String nombre, String paginaWeb, String pais, String provinciaEstado,
				Date fechaGuardado) {
			this.uid = uid;
			this.nombre = nombre;
			this.paginaWeb = paginaWeb;
			this.pais = pais;
			this.provinciaEstado = provinciaEstado;
			this.fechaGuardado = fechaGuardado;
		}

		public Integer getUid() {
			return uid;
		}

		public void setUid(Integer uid) {
			this.uid = uid;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getPaginaWeb() {
			return paginaWeb;
		}

		public void setPaginaWeb(String paginaWeb) {
			this.paginaWeb = paginaWeb;
		}

		public String getPais() {
			return pais;
		}

		public void setPais(String pais) {
			this.pais = pais;
		}

		public String getProvinciaEstado() {
			return provinciaEstado;
		}

		public void setProvinciaEstado(String provinciaEstado) {
			this.provinciaEstado = provinciaEstado;
		}

		public java.util.Date getFechaGuardado() {
			return fechaGuardado;
		}

		public void setFechaGuardado(java.util.Date fechaGuardado) {
			this.fechaGuardado = fechaGuardado;
		}

		@Override
		public String toString() {
			return "DatosUniversidad [uid=" + uid + ", nombre=" + nombre + ", paginaWeb=" + paginaWeb + ", pais=" + pais
					+ ", provinciaEstado=" + provinciaEstado + ", fechaGuardado=" + fechaGuardado + "]";
		}
	}

	/**
	 * Gestiona las excepciones de el aplicativo
	 */
	public class ExcepcionModulo2 extends Exception {

		private String mensajePersonalizado;
		private String mensajeError;
		private String codigoError;
		private String metodoError;

		/**
		 * Constructor vacio
		 */
		public ExcepcionModulo2() {
		}

		/**
		 * Constructor completo
		 * 
		 * @param mensajePersonalizado Mensaje personalizado para el usuario
		 * @param mensajeError         Mensaje de error que lanza la escepción
		 * @param codigoError          Codigo de error en caso de que exista
		 * @param metodoError          Metodo donde se ha generado la excepcion
		 */
		public ExcepcionModulo2(String mensajePersonalizado, String mensajeError, String codigoError,
				String metodoError) {
			this.mensajePersonalizado = mensajePersonalizado;
			this.mensajeError = mensajeError;
			this.codigoError = codigoError;
			this.metodoError = metodoError;
		}

		public String getMensajePersonalizado() {
			return mensajePersonalizado;
		}

		public void setMensajePersonalizado(String mensajePersonalizado) {
			this.mensajePersonalizado = mensajePersonalizado;
		}

		public String getMensajeError() {
			return mensajeError;
		}

		public void setMensajeError(String mensajeError) {
			this.mensajeError = mensajeError;
		}

		public String getCodigoError() {
			return codigoError;
		}

		public void setCodigoError(String codigoError) {
			this.codigoError = codigoError;
		}

		public String getMetodoError() {
			return metodoError;
		}

		public void setMetodoError(String metodoError) {
			this.metodoError = metodoError;
		}

		@Override
		public String toString() {
			return "ExcepcionModulo2 [mensajePersonalizado=" + mensajePersonalizado + ", mensajeError=" + mensajeError
					+ ", codigoError=" + codigoError + ", metodoError=" + metodoError + "]";
		}
	}

	public class DatosUsuario {

		private Integer uid;
		private String login;
		private String password;

		/**
		 * Constructor vacio
		 */
		public DatosUsuario() {
		}

		/**
		 * Constructor completo de todos los parametros
		 * 
		 * @param uid
		 * @param login
		 * @param pass
		 */
		public DatosUsuario(Integer uid, String login, String pass) {
			this.uid = uid;
			this.login = login;
			this.password = pass;
		}
		
		

		public Integer getUid() {
			return uid;
		}

		public void setUid(Integer uid) {
			this.uid = uid;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			return "DatosUsuario [uid=" + uid + ", login=" + login + ", password=" + password + "]";
		}
	}

}