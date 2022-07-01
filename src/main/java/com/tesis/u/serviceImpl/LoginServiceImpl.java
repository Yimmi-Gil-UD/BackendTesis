package com.tesis.u.serviceImpl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.tesis.u.dto.LoginDTO;
import com.tesis.u.dto.LoginResponseDTO;
import com.tesis.u.dto.RolDTO;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.MainSecurity;
import com.tesis.u.dto.EnfermeraDTO;
import com.tesis.u.dto.EstadoDTO;
import com.tesis.u.service.LoginService;



@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private FirebaseConfig firebase;
	
	//@Autowired
	private MainSecurity security;
	
	
	@Override
	public LoginResponseDTO getByUser(LoginDTO loginDTO) throws InterruptedException, ExecutionException {
		List<LoginResponseDTO> Listlogin = new ArrayList();
		String correoCliente, correoPost;
		LoginResponseDTO login = new LoginResponseDTO();
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference documentReference = db.collection("Enfermera").document(loginDTO.getId());
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		EnfermeraDTO enfermera;
		EstadoDTO estado;
		
		if (document.exists()) {
			enfermera = document.toObject(EnfermeraDTO.class);
			enfermera.setId(document.getId());
			
			DocumentReference datosEstado = firebase.getFirestore().collection("Estado").document(enfermera.getIdEstadoEnfermera());
			
			ApiFuture<DocumentSnapshot> futureEstado = datosEstado.get();
			DocumentSnapshot documentEstado = futureEstado.get();
			estado = documentEstado.toObject(EstadoDTO.class);
			
			enfermera.setDescripcionEstadoEnfermera(estado.getDescripcionEstado());
			
			
			if(enfermera.getDescripcionEstadoEnfermera().equals("Inactivo"))
			{
				return null;
			}
			else
			{
				//if (enfermera.getCorreo().equals(loginDTO.getCorreo())  && loginDTO.getPassword().equals(enfermera.getPassword())) {
				if(enfermera.getCorreo().equals(loginDTO.getCorreo())  && security.encrypt(loginDTO.getPassword()).equals(enfermera.getPassword()))
				{
								
				
					login.setId(enfermera.getId());
					login.setCorreo(enfermera.getCorreo());
					login.setRol(getByRol(enfermera.getIdRol()));
					//Listlogin.add(login);
					LoginResponseDTO loginFinal = new LoginResponseDTO(login.getId(),login.getCorreo(),login.getRol());
					

						return loginFinal;

				} else {
					return null;
				}
			}

		}

		return null;
	}

	@Override
	public String getByRol(String id) throws InterruptedException, ExecutionException {
		String descripcionRol;
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference documentReference = db.collection("Rol").document(id);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		RolDTO rol;
		if (document.exists()) {
			rol = document.toObject(RolDTO.class);
			descripcionRol = rol.getNombreRol();
			return descripcionRol;

		}

		return null;
	}

}
