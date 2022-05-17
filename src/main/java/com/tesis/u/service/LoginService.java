package com.tesis.u.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;

import com.tesis.u.dto.LoginDTO;
import com.tesis.u.dto.LoginResponseDTO;



public interface LoginService {

	List<LoginResponseDTO> getByUser(LoginDTO loginDTO) throws InterruptedException, ExecutionException;
	String getByRol(String id) throws InterruptedException, ExecutionException;
	
}
