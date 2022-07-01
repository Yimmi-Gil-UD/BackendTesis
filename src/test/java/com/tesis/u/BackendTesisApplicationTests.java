package com.tesis.u;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tesis.u.entity.Enfermera;
import com.tesis.u.serviceImpl.EnfermeraServiceImpl;

@SpringBootTest
class BackendTesisApplicationTests {

	@Autowired
	private EnfermeraServiceImpl service;
	
	@Test
	public void crearEnfermeraTest() {
	/*	Enfermera enfermera = new Enfermera();
		enfermera.setNombre("nombre prueba");
		enfermera.setApellido("apellido prueba");
		enfermera.setIdTipoDocumentoE("sdsdsdsd");
		enfermera.setNumeroIdentificacion(123456);
		enfermera.setCorreo("pruebaU@gmail.com");
		enfermera.setPassword("Pepitos123@");
		enfermera.setIdGenero("sdsdsdsd");
		enfermera.setIdRol("sdsdsdsd");
		enfermera.setIdEstadoEnfermera("sdsdsdsd");
		
		//service.save(enfermera);
		
		assertTrue("$2a$10$hzEbeM.hP9vCKxXyhNygGOQoR7wH/GFB4RvOSINt/.uSUXwpznY3O".equalsIgnoreCase(enfermera.getPassword()));
		*/
		
		
	}

}
