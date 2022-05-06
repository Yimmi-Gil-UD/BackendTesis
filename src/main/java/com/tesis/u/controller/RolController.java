package com.tesis.u.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesis.u.dto.RolDTO;
import com.tesis.u.service.RolService;

@RestController
@RequestMapping(value = "/rol")
public class RolController {
	
	@Autowired
	private RolService service; 
	
	@GetMapping(value = "/list")
	public ResponseEntity list() {
		return new ResponseEntity(service.list(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity save(@RequestBody RolDTO rol) {
		return new ResponseEntity(service.save(rol),HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity edit(@PathVariable(value = "id") String id,@RequestBody RolDTO rol) {
		return new ResponseEntity(service.update(id, rol), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity delete(@PathVariable(value = "id") String id) {
		return new ResponseEntity(service.delete(id),HttpStatus.OK);
	}

}
