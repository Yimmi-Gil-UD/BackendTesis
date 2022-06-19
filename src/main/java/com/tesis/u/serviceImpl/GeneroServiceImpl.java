package com.tesis.u.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.tesis.u.dto.GeneroDTO;
import com.tesis.u.dto.TipoDocumentoIdDTO;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.GeneroService;

@Service
public class GeneroServiceImpl implements GeneroService{
	
	@Autowired
	private FirebaseConfig firebase;

	@Override
	public List<GeneroDTO> list() {
		List<GeneroDTO> response = new ArrayList();
		GeneroDTO tipo;
		
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
		
		try {
			for(DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				tipo = doc.toObject(GeneroDTO.class);
				tipo.setId(doc.getId());
				response.add(tipo);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
	private CollectionReference getCollection() {
		return firebase.getFirestore().collection("Genero");
	}
	

}
