package com.tesis.u.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.tesis.u.dto.CategoriaDiscapacidadDTO;
import com.tesis.u.dto.GeneroDTO;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.CategoriaService;


@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	private FirebaseConfig firebase;
	
	@Override
	public List<CategoriaDiscapacidadDTO> list() {
		List<CategoriaDiscapacidadDTO> response = new ArrayList();
		CategoriaDiscapacidadDTO categoria;
		
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
		
		try {
			for(DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				categoria = doc.toObject(CategoriaDiscapacidadDTO.class);
				categoria.setId(doc.getId());
				response.add(categoria);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
	private CollectionReference getCollection() {
		return firebase.getFirestore().collection("CategoriaDiscapacidad");
	}
	

}
