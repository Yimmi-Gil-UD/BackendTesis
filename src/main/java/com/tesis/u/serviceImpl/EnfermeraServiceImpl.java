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
import com.tesis.u.dto.EnfermeraDTO;
import com.tesis.u.entity.Enfermera;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.EnfermeraService;

@Service
public class EnfermeraServiceImpl implements EnfermeraService{

	@Autowired
	private FirebaseConfig firebase;
	
	
	@Override
	public List<EnfermeraDTO> list() {
		List<EnfermeraDTO> response = new ArrayList();
		EnfermeraDTO enfermera;

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				enfermera = doc.toObject(EnfermeraDTO.class);
				enfermera.setId(doc.getId());
				response.add(enfermera);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public Boolean save(Enfermera enfermera) {
		Map<String, Object> docData = getDocData(enfermera);

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
	public Boolean update(String id, Enfermera enfermera) {
		Map<String, Object> docData = getDocData(enfermera);
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
		return firebase.getFirestore().collection("Enfermera");
	}
	
	private Map<String, Object> getDocData(Enfermera enfermera) {
		Map<String, Object> docData = new HashMap<>();
		docData.put("nombre", enfermera.getNombre());
		docData.put("apellido", enfermera.getApellido());
		docData.put("correo", enfermera.getCorreo());
		docData.put("password", enfermera.getPassword());
		docData.put("numeroIdentificacion", enfermera.getNumeroIdentificacion());
		docData.put("idGenero", enfermera.getIdGenero());
		docData.put("idRol", enfermera.getIdRol());
		return docData;
	}

}
