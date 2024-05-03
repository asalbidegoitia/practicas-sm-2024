package com.practicas2024.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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
	 * @throws ExcepcionModulo2 Se puede producir cuando surge un error inesperado al cargar el driver de MySQL
	 */
	public AccesoBD() throws ExcepcionModulo2 {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}catch(Exception ex) {
			
			ExcepcionModulo2 e = new ExcepcionModulo2();
			e.setMensajePersonalizado("Ha surgido un error inesperado");
			e.setMensajeError(ex.getMessage());
			e.setMetodoError("Constructor de la clase AccesoBD");
			throw e;
		}
	}
	
	/**
	 * Muestra por consola las tablas de la base de datos
	 * @throws ExcepcionModulo2 
	 */
	public void test() throws ExcepcionModulo2 {

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useSSL=true", USER, PASSWD) ;
			Statement stmt = conn.createStatement() ;
			String query = "show tables;" ;
			ResultSet rs = stmt.executeQuery(query) ;
			
			int i = 1;
			while(rs.next()) {
				System.out.println(rs.getString(i));
				i++;
			}
			
		}catch (Exception ex) {
			
			ExcepcionModulo2 e = new ExcepcionModulo2();
			e.setMensajePersonalizado("Ha surgido un error inesperado");
			e.setMensajeError(ex.getMessage());
			e.setMetodoError("test()");
			throw e;
		}
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
			String query = "INSERT INTO universidades(uid,nombre,pagina_web,pais,provincia_estado,fecha_guardado) VALUES(?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setObject(1, null);
			statement.setString(2, collegeName);
			statement.setString(3, webPage);
			statement.setString(4, countryName);
			statement.setString(5, provinceName);
			statement.setObject(6, LocalDate.now());
			
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
	 * Gestiona las excepciones de el aplicativo
	 */
 	public class ExcepcionModulo2 extends Exception{
		
		private String mensajePersonalizado;
		private String mensajeError;
		private String codigoError;
		private String metodoError;
		
		/**
		 * Constructor vacio
		 */
		public ExcepcionModulo2() {}
		
		/**
		 * Constructor completo
		 * @param mensajePersonalizado Mensaje personalizado para el usuario
		 * @param mensajeError Mensaje de error que lanza la escepción
		 * @param codigoError Codigo de error en caso de que exista
		 * @param metodoError Metodo donde se ha generado la excepcion
		 */
		public ExcepcionModulo2(String mensajePersonalizado, String mensajeError, String codigoError, String metodoError) {
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
}
