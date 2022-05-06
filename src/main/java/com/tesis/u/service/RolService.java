package com.tesis.u.service;

import java.util.List;

import com.tesis.u.dto.RolDTO;

public interface RolService {

	List<RolDTO> list();
	
	Boolean save(RolDTO rol);
	
	Boolean update(String id, RolDTO rol);
	
	Boolean delete(String id);
	
	
	
	
}
