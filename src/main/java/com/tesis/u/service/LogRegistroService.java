package com.tesis.u.service;

import java.util.List;

import com.tesis.u.dto.LogRegistroDTO;
import com.tesis.u.entity.LogRegistro;

public interface LogRegistroService {
	
	List<LogRegistroDTO> list();
	Boolean save(LogRegistro log);
	

}
