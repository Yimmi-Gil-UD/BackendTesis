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
import com.tesis.u.dto.TipoTerapiaDTO;
import com.tesis.u.entity.TipoTerapia;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.TipoTerapiaService;

@Service
public class TipoTerapiaServiceImpl implements TipoTerapiaService{

	@Autowired
	private FirebaseConfig firebase;
	
	@Override
	public List<TipoTerapiaDTO> list() {
		List<TipoTerapiaDTO> response = new ArrayList();
		TipoTerapiaDTO terapia;

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				terapia = doc.toObject(TipoTerapiaDTO.class);
				terapia.setId(doc.getId());
				response.add(terapia);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	
	@Override
	public Boolean save(TipoTerapia terapia) {
		Map<String, Object> docData = getDocData(terapia);

		ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);

		try {
			if (null != writeResultApiFuture.get()) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean update(String id, TipoTerapia terapia) {
		Map<String, Object> docData = getDocData(terapia);
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
		return firebase.getFirestore().collection("TipoTerapia");
	}
	
	private Map<String, Object> getDocData(TipoTerapia terapia) {
		Map<String, Object> docData = new HashMap<>();
		docData.put("nombreTerapia", terapia.getNombreTerapia());
		return docData;
	}

}
