package com.tesis.u.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesis.u.service.GeneroService;

@RestController
@RequestMapping(value = "/genero")
@CrossOrigin
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping(value = "/list")
	public ResponseEntity list() {
		return new ResponseEntity(generoService.list(), HttpStatus.OK);
	}

}
