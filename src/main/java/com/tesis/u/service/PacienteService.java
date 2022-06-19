package com.tesis.u.service;

import java.util.List;

import com.tesis.u.dto.PacienteDTO;
import com.tesis.u.entity.Paciente;


public interface PacienteService {
	
	List<PacienteDTO> list();
	List<PacienteDTO> detail(String id);
	Boolean save(Paciente paciente);
	Boolean update(String id, Paciente paciente);
	Boolean delete(String id);

}
