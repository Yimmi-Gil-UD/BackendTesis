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


import com.tesis.u.entity.Paciente;
import com.tesis.u.service.PacienteService;

@RestController
@RequestMapping(value = "/paciente")
@CrossOrigin
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping(value = "/list")
	public ResponseEntity list() {
		return new ResponseEntity(pacienteService.list(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity save(@RequestBody Paciente Paciente) {
		return new ResponseEntity(pacienteService.save(Paciente),HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity edit(@PathVariable(value = "id") String id,@RequestBody Paciente Paciente) {
		return new ResponseEntity(pacienteService.update(id, Paciente), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity delete(@PathVariable(value = "id") String id) {
		return new ResponseEntity(pacienteService.delete(id),HttpStatus.OK);
	}
}
