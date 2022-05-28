package com.tesis.u.service;

import java.util.List;

import com.tesis.u.dto.FundacionDTO;
import com.tesis.u.entity.Fundacion;

public interface FundacionService {

	List<FundacionDTO> list();
	Boolean save(Fundacion fundacion);
	Boolean update(String id, Fundacion fundacion);
	Boolean delete(String id);
	
	
}
