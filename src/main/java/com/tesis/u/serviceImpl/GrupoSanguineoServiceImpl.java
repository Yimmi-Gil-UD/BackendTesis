package com.tesis.u.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.tesis.u.dto.GrupoSanguineoDTO;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.GrupoSanguineoService;

@Service
public class GrupoSanguineoServiceImpl implements GrupoSanguineoService{
	
	@Autowired
	private FirebaseConfig firebase;

	@Override
	public List<GrupoSanguineoDTO> list() {
		List<GrupoSanguineoDTO> response = new ArrayList();
		GrupoSanguineoDTO grupo;
		
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
		
		try {
			for(DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				grupo = doc.toObject(GrupoSanguineoDTO.class);
				grupo.setId(doc.getId());
				response.add(grupo);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	private CollectionReference getCollection() {
		return firebase.getFirestore().collection("GrupoSanguineo");
	}

}
