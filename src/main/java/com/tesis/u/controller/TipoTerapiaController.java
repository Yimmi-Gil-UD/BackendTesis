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

import com.tesis.u.entity.TipoTerapia;
import com.tesis.u.service.TipoTerapiaService;

@RestController
@RequestMapping(value = "/tipoTerapia")
@CrossOrigin
public class TipoTerapiaController {

	@Autowired
	private TipoTerapiaService tipoTerapiaService; 
	
	@GetMapping(value = "/list")
	public ResponseEntity list() {
		return new ResponseEntity(tipoTerapiaService.list(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/detail/{id}")
	public ResponseEntity detail(@PathVariable(value = "id") String id) {
		return new ResponseEntity(tipoTerapiaService.detail(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity save(@RequestBody TipoTerapia terapia) {
		return new ResponseEntity(tipoTerapiaService.save(terapia),HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity edit(@PathVariable(value = "id") String id,@RequestBody TipoTerapia terapia) {
		return new ResponseEntity(tipoTerapiaService.update(id, terapia), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity delete(@PathVariable(value = "id") String id) {
		return new ResponseEntity(tipoTerapiaService.delete(id),HttpStatus.OK);
	}
	
}
