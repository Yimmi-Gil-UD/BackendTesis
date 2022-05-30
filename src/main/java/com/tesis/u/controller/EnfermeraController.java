package com.tesis.u.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesis.u.entity.Enfermera;
import com.tesis.u.service.EnfermeraService;

@RestController
@RequestMapping(value = "/enfermera")
@CrossOrigin
public class EnfermeraController {
	
	@Autowired
	private EnfermeraService enfermeraService;
	
	@GetMapping(value = "/list")
	public ResponseEntity list() {
		return new ResponseEntity(enfermeraService.list(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity save(@RequestBody Enfermera enfermera) {
		return new ResponseEntity(enfermeraService.save(enfermera),HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity edit(@PathVariable(value = "id") String id,@RequestBody Enfermera enfermera) {
		return new ResponseEntity(enfermeraService.update(id, enfermera), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity delete(@PathVariable(value = "id") String id) {
		return new ResponseEntity(enfermeraService.delete(id),HttpStatus.OK);
	}

}
