package com.practicas2024.controller;

import java.util.ArrayList;
import com.practicas2024.controller.AccesoBD.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Controller
public class LoginController {

	@RequestMapping(value = "/obtenerUsuarios", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<DatosUsuario>> obtenerUsuarios() {
	    try {
			AccesoBD bd = new AccesoBD();
	    	ArrayList<DatosUsuario> usuarios = bd.leerUsuarios();
	        return new ResponseEntity<>(usuarios, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}