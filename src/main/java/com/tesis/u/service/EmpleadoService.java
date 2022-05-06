package com.tesis.u.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.tesis.u.dto.EmpleadoDTO;
import com.tesis.u.dto.RolDTO;



public interface EmpleadoService {

	List<EmpleadoDTO> list();
	
	Boolean save(EmpleadoDTO empleado);
	
	Boolean update(String id, EmpleadoDTO empleado);
	
	Boolean delete(String id);
	
}
