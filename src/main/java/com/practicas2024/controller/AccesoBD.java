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
			Connection conn = DriverManager
					.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useSSL=true", USER, PASSWD);
			Statement stmt = conn.createStatement();
			String query = "select * from universidades;";
			ResultSet rs = stmt.executeQuery(query);

			universidades = new ArrayList<AccesoBD.DatosUniversidad>();

			int i = 1;
			while (rs.next()) {

				DatosUniversidad universidad = new DatosUniversidad();
				universidad.setUid(((BigDecimal) rs.getObject("uid")).intValue());
				universidad.setNombre(rs.getString("nombre"));
				universidad.setPaginaWeb(rs.getString("pagina_web"));
				universidad.setPais(rs.getString("pais"));
				universidad.setProvinciaEstado(rs.getString("provincia_estado"));
				universidad.setFechaGuardado(rs.getDate("fecha_guardado"));
				universidades.add(universidad);

				i++;
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

			ps.setString(0, c.getName());
			ps.setString(1, c.getWebpage());
			ps.setString(2, c.getCountry());
			ps.setString(3, c.getState());

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
}
