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

import com.tesis.u.entity.NotaEnfermeria;
import com.tesis.u.service.NotaEnfermeriaService;

@RestController
@RequestMapping(value = "/nota")
@CrossOrigin
public class NotaEnfermeriaController {
	
	@Autowired
	private NotaEnfermeriaService notaEnfermeriaService;
	
	@GetMapping(value = "/list")
	public ResponseEntity list() {
		return new ResponseEntity(notaEnfermeriaService.list(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity save(@RequestBody NotaEnfermeria notaEnfermeria) {
		return new ResponseEntity(notaEnfermeriaService.save(notaEnfermeria),HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity edit(@PathVariable(value = "id") String id,@RequestBody NotaEnfermeria notaEnfermeria) {
		return new ResponseEntity(notaEnfermeriaService.update(id, notaEnfermeria), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity delete(@PathVariable(value = "id") String id) {
		return new ResponseEntity(notaEnfermeriaService.delete(id),HttpStatus.OK);
	}

}
