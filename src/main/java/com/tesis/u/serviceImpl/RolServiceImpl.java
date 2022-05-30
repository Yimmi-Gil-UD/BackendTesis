package com.tesis.u.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.tesis.u.dto.RolDTO;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.RolService;

@Service
public class RolServiceImpl<I,O> implements RolService{

	@Autowired
	private FirebaseConfig firebase;

	@Override
	public List<RolDTO> list() {
		List<RolDTO> response = new ArrayList();
		RolDTO rol;
		
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
		
		try {
			for(DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				rol = doc.toObject(RolDTO.class);
				rol.setId(doc.getId());
				response.add(rol);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	
	
	@Override
	public Boolean save(RolDTO rol) {
		Map<String, Object> docData = getDocData(rol);
		
		
		ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);
		
		try {
			if(null != writeResultApiFuture.get()) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			return Boolean.FALSE;
		}
	}
	
	
	

	@Override
	public Boolean update(String id, RolDTO rol) {
		Map<String, Object> docData = getDocData(rol);
		ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).set(docData);
		
		try {
			if(null != writeResultApiFuture.get()) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(String id) {
		ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();
		
		try {
			if(null != writeResultApiFuture.get()) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			return Boolean.FALSE;
		}
	}
	
	private CollectionReference getCollection() {
		return firebase.getFirestore().collection("Rol");
	}
	
	private Map<String, Object> getDocData(RolDTO rol){
		Map<String, Object> docData = new HashMap<>();
		docData.put("nombreRol", rol.getNombreRol());
		return docData;
	}

	
}
