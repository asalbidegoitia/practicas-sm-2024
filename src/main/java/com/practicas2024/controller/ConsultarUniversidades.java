package com.practicas2024.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.practicas2024.controller.AccesoBD.DatosUniversidad;
import com.practicas2024.controller.AccesoBD.ExcepcionModulo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Controller
public class ConsultarUniversidades {
	/*
	@RequestMapping(value = "/obtenerUniversidades", method = RequestMethod.GET)
	 public ArrayList<DatosUniversidad> obtenerUniversidades() throws ExcepcionModulo2 {
	        ArrayList<DatosUniversidad> universidades = null;
	        System.out.println("hola");
	        AccesoBD bd = new AccesoBD();
	        try {
	            universidades = bd.leerUniversidades();
	            for (DatosUniversidad universidad : universidades) {
	                System.out.println("UID: " + universidad.getUid());
	                System.out.println("Nombre: " + universidad.getNombre());
	                System.out.println("Página Web: " + universidad.getPaginaWeb());
	                System.out.println("País: " + universidad.getPais());
	                System.out.println("Provincia/Estado: " + universidad.getProvinciaEstado());
	                System.out.println("Fecha Guardado: " + universidad.getFechaGuardado());
	                System.out.println("--------------------");
	            }
	        } catch (ExcepcionModulo2 e) {
	            e.printStackTrace();
	        }
	        return universidades;
	    }*/
	

	    
	    @RequestMapping(value = "/obtenerUniversidades", method = RequestMethod.GET)
	    public ResponseEntity<ArrayList<DatosUniversidad>> obtenerUniversidades() throws ExcepcionModulo2 {
	    	AccesoBD bd = new AccesoBD();
	        try {
	        	ArrayList<DatosUniversidad> universidades = bd.leerUniversidades();
	        	for (DatosUniversidad universidad : universidades) {
	                System.out.println("UID: " + universidad.getUid());
	                System.out.println("Nombre: " + universidad.getNombre());
	                System.out.println("Página Web: " + universidad.getPaginaWeb());
	                System.out.println("País: " + universidad.getPais());
	                System.out.println("Provincia/Estado: " + universidad.getProvinciaEstado());
	                System.out.println("Fecha Guardado: " + universidad.getFechaGuardado());
	                System.out.println("--------------------");
	            }
	            return new ResponseEntity<>(universidades, HttpStatus.OK);
	        } catch (ExcepcionModulo2 e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
