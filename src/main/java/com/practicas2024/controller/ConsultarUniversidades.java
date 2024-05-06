package com.practicas2024.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.practicas2024.controller.AccesoBD.DatosUniversidad;
import com.practicas2024.controller.AccesoBD.ExcepcionModulo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Controller
public class ConsultarUniversidades {

	    @RequestMapping(value = "/obtenerUniversidades", method = RequestMethod.GET)
	    public ResponseEntity<ArrayList<DatosUniversidad>> obtenerUniversidades() throws ExcepcionModulo2 {
	    	AccesoBD bd = new AccesoBD();
	        try {
	        	ArrayList<DatosUniversidad> universidades = bd.leerUniversidades();
	            return new ResponseEntity<>(universidades, HttpStatus.OK);
	        } catch (ExcepcionModulo2 e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @RequestMapping(value = "eliminarUniversidad/{id}", method = RequestMethod.POST)
	    public String eliminarUniversidad(@PathVariable String id) {
	        try {
	        	AccesoBD bd = new AccesoBD();
	            bd.eliminarUniversidad(id);

	        } catch (ExcepcionModulo2 e) {
	            e.printStackTrace();
	            return null;
	        }
	        return "Universidad eliminada correctamente con el ID: " + id;
	    }
	    
}
