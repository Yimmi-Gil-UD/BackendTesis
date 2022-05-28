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
import com.tesis.u.dto.FundacionDTO;
import com.tesis.u.dto.RolDTO;
import com.tesis.u.entity.Fundacion;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.FundacionService;

@Service
public class FundacionServiceImpl<I, O> implements FundacionService {

	@Autowired
	private FirebaseConfig firebase;

	@Override
	public List<FundacionDTO> list() {
		List<FundacionDTO> response = new ArrayList();
		FundacionDTO fundacion;

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				fundacion = doc.toObject(FundacionDTO.class);
				fundacion.setId(doc.getId());
				response.add(fundacion);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	
	@Override
	public Boolean save(Fundacion fundacion) {
		Map<String, Object> docData = getDocData(fundacion);

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
	public Boolean update(String id, Fundacion fundacion) {
		Map<String, Object> docData = getDocData(fundacion);
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
		return firebase.getFirestore().collection("Fundacion");
	}

	private Map<String, Object> getDocData(Fundacion fundacion) {
		Map<String, Object> docData = new HashMap<>();
		docData.put("direccion", fundacion.getDireccion());
		docData.put("nombreFundacion", fundacion.getNombreFundacion());
		docData.put("telefono", fundacion.getTelefono());
		return docData;
	}

}
