package com.tesis.u.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesis.u.dto.LogRegistroDTO;
import com.tesis.u.entity.Fundacion;
import com.tesis.u.entity.LogRegistro;
import com.tesis.u.service.LogRegistroService;

@RestController
@RequestMapping(value = "/log")
@CrossOrigin
public class LogRegistroController {
	
	@Autowired
	private LogRegistroService logService;
	
	@GetMapping(value = "/list")
	public ResponseEntity list() {
		return new ResponseEntity(logService.list(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity save(@RequestBody LogRegistro log) {
		return new ResponseEntity(logService.save(log),HttpStatus.OK);
	}

}
