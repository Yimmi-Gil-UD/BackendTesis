package com.tesis.u.service;

import java.util.List;

import com.tesis.u.dto.NotaEnfermeriaDTO;
import com.tesis.u.entity.NotaEnfermeria;


public interface NotaEnfermeriaService {

	List<NotaEnfermeriaDTO> list();
	Boolean save(NotaEnfermeria nota);
	Boolean update(String id, NotaEnfermeria nota);
	Boolean delete(String id);
	
}
