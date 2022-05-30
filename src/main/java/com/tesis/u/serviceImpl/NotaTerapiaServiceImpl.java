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
import com.tesis.u.dto.NotaTerapiaDTO;
import com.tesis.u.entity.NotaTerapia;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.NotaTerapiaService;

@Service
public class NotaTerapiaServiceImpl implements NotaTerapiaService{
	
	@Autowired
	private FirebaseConfig firebase;

	@Override
	public List<NotaTerapiaDTO> list() {
		List<NotaTerapiaDTO> response = new ArrayList();
		NotaTerapiaDTO notaTerapia;

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				notaTerapia = doc.toObject(NotaTerapiaDTO.class);
				notaTerapia.setId(doc.getId());
				response.add(notaTerapia);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public Boolean save(NotaTerapia notaTerapia) {
		Map<String, Object> docData = getDocData(notaTerapia);

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
	public Boolean update(String id, NotaTerapia notaTerapia) {
		Map<String, Object> docData = getDocData(notaTerapia);
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
		return firebase.getFirestore().collection("NotaTerapia");
	}
	
	private Map<String, Object> getDocData(NotaTerapia notaTerapia) {
		Map<String, Object> docData = new HashMap<>();
		docData.put("idPaciente", notaTerapia.getIdPaciente());
		docData.put("objetivo", notaTerapia.getObjetivo());
		docData.put("estructuraCorporal", notaTerapia.getEstructuraCorporal());
		docData.put("funcionCorporal", notaTerapia.getFuncionCorporal());
		docData.put("pronostico", notaTerapia.getPronostico());
		docData.put("planTrabajo", notaTerapia.getPlanTrabajo());
		docData.put("fechaNotaTerapia", notaTerapia.getFechaNotaTerapia());
		docData.put("horaNotaTerapia", notaTerapia.getHoraNotaTerapia());
		docData.put("observacion", notaTerapia.getObservacion());
		docData.put("idTipoTerapia", notaTerapia.getIdTipoTerapia());
		docData.put("idEnfermera", notaTerapia.getIdEnfermera());
		return docData;
	}

}
