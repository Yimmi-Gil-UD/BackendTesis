package com.tesis.u.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.tesis.u.dto.RolDTO;
import com.tesis.u.dto.TipoDocumentoIdDTO;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.TipoIdentificacionService;

@Service
public class TipoIdentificacionServiceImpl implements TipoIdentificacionService{

	@Autowired
	private FirebaseConfig firebase;
	
	@Override
	public List<TipoDocumentoIdDTO> list() {
		List<TipoDocumentoIdDTO> response = new ArrayList();
		TipoDocumentoIdDTO tipo;
		
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
		
		try {
			for(DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				tipo = doc.toObject(TipoDocumentoIdDTO.class);
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
		return firebase.getFirestore().collection("TipoDocumentoId");
	}

}
