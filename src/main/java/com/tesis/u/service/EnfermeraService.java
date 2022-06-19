package com.tesis.u.service;

import java.util.List;

import com.tesis.u.dto.EnfermeraDTO;
import com.tesis.u.dto.PacienteDTO;
import com.tesis.u.entity.Enfermera;


public interface EnfermeraService {

	List<EnfermeraDTO> list();
	List<EnfermeraDTO> detail(String id);
	Boolean save(Enfermera enfermera);
	Boolean update(String id, Enfermera enfermera);
	Boolean delete(String id);
}
