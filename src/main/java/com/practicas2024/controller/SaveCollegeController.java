package com.practicas2024.controller;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.HttpURLConnection; 
import java.net.URL; 
import java.util.ArrayList; 
import java.util.Iterator; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody; 
import com.google.gson.Gson; 
import com.google.gson.JsonArray; 
import com.google.gson.JsonElement; 
import com.google.gson.JsonObject;
import com.practicas2024.controller.AccesoBD.ExcepcionModulo2;

@Controller
public class SaveCollegeController {
	@RequestMapping(value = "/saveCollegeDetails", method = RequestMethod.GET)
	public @ResponseBody
	JsonObject getLocalityDetailsByZipCode(String countryName,String name) throws IOException, ExcepcionModulo2 {
		AccesoBD abd = new AccesoBD();
		abd.test();
		return null;
	}
}
