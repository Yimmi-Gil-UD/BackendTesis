package com.tesis.u.controller;


import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesis.u.dto.LoginDTO;
import com.tesis.u.dto.LoginResponseDTO;
import com.tesis.u.dto.UsuarioDTO;
import com.tesis.u.entity.Usuario;
import com.tesis.u.service.LoginService;


@RestController
@RequestMapping(value = "/auth")
public class LoginController {
	
	@Autowired
	private LoginService service; 
	
	@PostMapping(value = "/login")
	public ResponseEntity login(@RequestBody LoginDTO loginDTO) throws InterruptedException, ExecutionException {
		return (ResponseEntity) new ResponseEntity(service.getByUser(loginDTO), HttpStatus.OK);
	}
	

}
