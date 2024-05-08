package com.practicas2024.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.htmlunit.webdriver.WebConnectionHtmlUnitDriver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.practicas2024.controller.AccesoBD.ExcepcionModulo2;

import pojos.College;

@Controller
public class CollegeController {

	@RequestMapping(value = "/getCollegeDetailsBycountryNameAndSearchString", method = RequestMethod.GET)
	public @ResponseBody JsonObject getLocalityDetailsByZipCode(String countryName, String name) throws IOException {

		// Prueba de conexión
		/*
		 * try { AccesoBD a = new AccesoBD(); System.out.println(a.leerUniversidades());
		 * 
		 * } catch (ExcepcionModulo2 e) { System.out.println(e.toString()); }
		 */

		/*
		 * Se captura la escepción en caso de que ocurra para poder mostrarlo en el
		 * panel Ejercicio 02 (Hugo Vélez)
		 */
		JsonObject finalJsonObject = new JsonObject();
		try {

			JsonArray jsonArray = new JsonArray();
			jsonArray = getCollegeDetailsByParams(countryName, name);
			// String country = jsonObject.get("country").toString();
			// country = country.replaceAll("^\"|\"$", "");
			ArrayList collegeList = new ArrayList();
			ArrayList stateList = new ArrayList();
			ArrayList webPageList = new ArrayList();
			// ArrayList longitudeList = new ArrayList();
			// jsonPlacesArray = jsonObject.get("places").getAsJsonArray();
			Iterator<JsonElement> objectIterator = jsonArray.iterator();

			while (objectIterator.hasNext()) {
				JsonElement object = objectIterator.next();
				JsonObject jObj = object.getAsJsonObject();
				// System.out.println(jObj.get("place name").toString() +
				// jObj.get("state").toString() );
				collegeList.add(jObj.get("name").toString().replaceAll("^\"|\"$", ""));
				stateList.add(jObj.get("state-province").toString().replaceAll("^\"|\"$", ""));
				webPageList.add(jObj.get("web_pages").toString().replaceAll("^\"|\"$", ""));
			}
			// finalJsonObject.addProperty("country", country);
			finalJsonObject.addProperty("associatedcolleges", collegeList.toString());
			finalJsonObject.addProperty("associatedcollegesize", collegeList.size());
			finalJsonObject.addProperty("state", stateList.toString());
			finalJsonObject.addProperty("statename", stateList.get(0).toString());
			finalJsonObject.addProperty("associatedwebpages", webPageList.toString());
			finalJsonObject.addProperty("associatedwebpagesize", webPageList.size());

		} catch (IndexOutOfBoundsException ex) {
			// Se captura la excepcion que se genera y se añade a las propiedades del objeto
			// para poder mostrarlo en la pagina
			JsonObject jso = obtenerError("No se han encontrado datos que coincidan con la busqueda", finalJsonObject);
			finalJsonObject = jso;
		} catch (Exception ex) {
			// Se captura la excepcion que se genera y se añade a las propiedades del objeto
			// para poder mostrarlo en la pagina
			JsonObject jso = obtenerError(ex, finalJsonObject);
			finalJsonObject = jso;
		}

		return finalJsonObject;
	}
	
	@RequestMapping(value = "/sendFileContentoToCollegeController", method = RequestMethod.GET)
	public @ResponseBody void uploadFile(String fileContent, String fileName){
		if(fileContent != null) {
			
			String filesLocation = "C:\\EjercicioGitFiles\\";
	        File directory = new File(filesLocation);
	        File file = new File(filesLocation + fileName);

	        // Crear el directorio si no existe
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }

	        boolean todoOK = false;
	        try {
	            // Crear el archivo si no existe
	            if (!file.exists()) {
	                file.createNewFile();
	            }

	            FileWriter fw = new FileWriter(file, true);
	            fw.append(fileContent);
	            fw.close();
	            
	            todoOK = true;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        if(todoOK) {
	        	try {
					AccesoBD abd = new AccesoBD();
					System.out.println(abd.insertarArchivo(file));
					
				} catch (ExcepcionModulo2 e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		}
	}

	/**
	 * Peticion para ademas de buscar se almacene el resultado en la base de datos
	 * remota
	 * 
	 * @param countryName Primer parametro de entrada el nombre del pais
	 * @param name        Segundo parametro el nombre de la universidad
	 * @return Un objeto con el resultado de la busqueda
	 */
	@RequestMapping(value = "/saveCollegeDetails", method = RequestMethod.GET)
	public @ResponseBody JsonObject getAndSaveCollegeDetails(String countryName, String name) throws IOException {
		// Obtener los datos
		JsonObject json = getLocalityDetailsByZipCode(countryName, name);
		// Crear un college y eliminar caracteres [ " ] para almacenar en la base de
		// datos
		College coll = new College(json.get("associatedcolleges").getAsString().replaceAll("\\[|\\\"|\\]", ""),
				json.get("associatedwebpages").getAsString().replaceAll("\\[|\\\"|\\]", ""), countryName,
				json.get("state").getAsString().replaceAll("\\[|\\\"|\\]", ""));

		try {
			// Crear un acceso a la base de datos
			AccesoBD abd = new AccesoBD();
			// Insertar la universidad
			abd.insertCollege(coll);

		} catch (ExcepcionModulo2 ex) {

		}

		//Devolver la respuesta
		return json;
	}

	private JsonArray getCollegeDetailsByParams(String countryName, String name) throws IOException {

		StringBuilder responseData = new StringBuilder();
		JsonArray jsonArray = null;

		name = name.replaceAll(" ", "%20");
		countryName = countryName.replaceAll(" ", "%20");
		
		URL url = null;
		url = new URL("http://universities.hipolabs.com/search?name=" + name + "&country=" + countryName);

		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();

		System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);

		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

			String line;

			while ((line = in.readLine()) != null) {
				responseData.append(line);
			}

			jsonArray = new Gson().fromJson(responseData.toString(), JsonArray.class);

		}

		return jsonArray;
	}

	/**
	 * Se añade una propiedad al JSON para posteriormente mostrarlo el pa pagina web
	 * Ejercicio 02 (Hugo Vélez)
	 * 
	 * @param ex  Excepción que se ha producido
	 * @param jso Objeto JSON que almacena las propiedades a mostrar
	 * @return Objeto JSON con la nueva propiedad añadida
	 */
	private JsonObject obtenerError(Exception ex, JsonObject jso) {
		jso.addProperty("parrafoErrores", ex.getMessage());
		return jso;
	}

	/**
	 * Se añade una propiedad al JSON para posteriormente mostrarlo el pa pagina web
	 * Ejercicio 02 (Hugo Vélez)
	 * 
	 * @param mensaje Mensaje a mostrar
	 * @param jso     Objeto JSON que almacena las propiedades a mostrar
	 * @return Objeto JSON con la nueva propiedad añadida
	 */
	private JsonObject obtenerError(String mensaje, JsonObject jso) {
		jso.addProperty("parrafoErrores", mensaje);
		return jso;
	}
}