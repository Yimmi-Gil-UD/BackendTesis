package com.tesis.u.service;

import java.util.List;

import com.tesis.u.dto.TipoTerapiaDTO;
import com.tesis.u.entity.TipoTerapia;


public interface TipoTerapiaService {

	List<TipoTerapiaDTO> list();
	Boolean save(TipoTerapia terapia);
	Boolean update(String id, TipoTerapia terapia);
	Boolean delete(String id);
	
}
