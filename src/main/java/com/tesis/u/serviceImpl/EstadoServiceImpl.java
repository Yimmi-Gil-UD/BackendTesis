package com.tesis.u.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.tesis.u.dto.EstadoDTO;
import com.tesis.u.dto.TipoDocumentoIdDTO;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService {
	
	@Autowired
	private FirebaseConfig firebase;

	@Override
	public List<EstadoDTO> list() {
		List<EstadoDTO> response = new ArrayList();
		EstadoDTO estado;
		
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
		
		try {
			for(DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				estado = doc.toObject(EstadoDTO.class);
				estado.setId(doc.getId());
				response.add(estado);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	private CollectionReference getCollection() {
		return firebase.getFirestore().collection("Estado");
	}

}
