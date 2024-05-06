package com.practicas2024.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.practicas2024.controller.AccesoBD.ExcepcionModulo2; 
/**
 * Controller for the insertion of a new college into the database.
 * @author phosliag
 *
 */
@Controller
public class InsertCollegeController { 

	@RequestMapping(value = "/insertCollegeDetails", method = RequestMethod.POST)
	public @ResponseBody
	JsonObject insertCollege(String collegeName,String countryName, String provinceName, String webPage) throws IOException { 
		System.out.println("college: "+ collegeName);
		System.out.println("country: "+ countryName);
		System.out.println("Province/State: "+ provinceName);
		System.out.println("Web: "+ webPage);
		
		JsonObject finalJsonObject = new JsonObject();
		AccesoBD database;
		try {
			database = new AccesoBD();
			database.addCollege(collegeName,countryName,provinceName,webPage);
			finalJsonObject.addProperty("insertState", "College inserted successfully");
		} catch (ExcepcionModulo2 e) {
			// TODO Auto-generated catch block
			finalJsonObject.addProperty("insertState", e.toString());
		}		
		
		return finalJsonObject;
	} 
}
