package com.tesis.u.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
import com.tesis.u.dto.EnfermeraDTO;
import com.tesis.u.entity.Enfermera;
import com.tesis.u.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public List<LoginResponseDTO> getByUser(LoginDTO loginDTO) throws InterruptedException, ExecutionException {
		List<LoginResponseDTO> Listlogin = new ArrayList();
		String correoCliente, correoPost;
		LoginResponseDTO login = new LoginResponseDTO();
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference documentReference = db.collection("Enfermera").document(loginDTO.getId());
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		EnfermeraDTO enfermera;
		
		if (document.exists()) {
			enfermera = document.toObject(EnfermeraDTO.class);
			enfermera.setId(document.getId());
			
			if (enfermera.getCorreo().equals(loginDTO.getCorreo())  && loginDTO.getPassword().equals(enfermera.getPassword())) {
				login.setId(enfermera.getId());
				login.setCorreo(enfermera.getCorreo());
				login.setRol(getByRol(enfermera.getIdRol()));
				Listlogin.add(login);
				return Listlogin;
			} else {
				return null;
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
