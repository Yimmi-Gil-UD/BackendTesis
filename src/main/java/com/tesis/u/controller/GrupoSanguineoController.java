package com.tesis.u.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesis.u.service.GrupoSanguineoService;

@RestController
@RequestMapping(value = "/grupoSanguineo")
@CrossOrigin
public class GrupoSanguineoController {
	
	@Autowired
	private GrupoSanguineoService grupoSanguineoService;
	
	@GetMapping(value = "/list")
	public ResponseEntity list() {
		return new ResponseEntity(grupoSanguineoService.list(), HttpStatus.OK);
	}
	

}