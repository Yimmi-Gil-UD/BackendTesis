package com.tesis.u.service;

import java.util.List;

import com.tesis.u.dto.NotaTerapiaDTO;
import com.tesis.u.entity.NotaTerapia;


public interface NotaTerapiaService {

	List<NotaTerapiaDTO> list();
	Boolean save(NotaTerapia notaTerapia);
	Boolean update(String id, NotaTerapia notaTerapia);
	Boolean delete(String id);
}
