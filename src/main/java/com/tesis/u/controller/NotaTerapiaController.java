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


import com.tesis.u.entity.NotaTerapia;
import com.tesis.u.service.NotaTerapiaService;

@RestController
@RequestMapping(value = "/notaTerapia")
@CrossOrigin
public class NotaTerapiaController {

	@Autowired
	private NotaTerapiaService notaTerapiaService;
	
	@GetMapping(value = "/list")
	public ResponseEntity list() {
		return new ResponseEntity(notaTerapiaService.list(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity save(@RequestBody NotaTerapia notaTerapia) {
		return new ResponseEntity(notaTerapiaService.save(notaTerapia),HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity edit(@PathVariable(value = "id") String id,@RequestBody NotaTerapia notaTerapia) {
		return new ResponseEntity(notaTerapiaService.update(id, notaTerapia), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity delete(@PathVariable(value = "id") String id) {
		return new ResponseEntity(notaTerapiaService.delete(id),HttpStatus.OK);
	}
	
	
}
