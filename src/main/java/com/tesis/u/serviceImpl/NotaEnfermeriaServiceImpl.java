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
import com.tesis.u.dto.NotaEnfermeriaDTO;
import com.tesis.u.entity.NotaEnfermeria;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.NotaEnfermeriaService;

@Service
public class NotaEnfermeriaServiceImpl implements NotaEnfermeriaService{
	
	@Autowired
	private FirebaseConfig firebase;

	@Override
	public List<NotaEnfermeriaDTO> list() {
		List<NotaEnfermeriaDTO> response = new ArrayList();
		NotaEnfermeriaDTO nota;

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				nota = doc.toObject(NotaEnfermeriaDTO.class);
				nota.setId(doc.getId());
				response.add(nota);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public Boolean save(NotaEnfermeria nota) {
		Map<String, Object> docData = getDocData(nota);

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
	public Boolean update(String id, NotaEnfermeria nota) {
		Map<String, Object> docData = getDocData(nota);
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
		return firebase.getFirestore().collection("NotaEnfermeria");
	}
	
	private Map<String, Object> getDocData(NotaEnfermeria nota) {
		Map<String, Object> docData = new HashMap<>();
		docData.put("idPaciente", nota.getIdPaciente() );
		docData.put("numeroCuarto", nota.getNumeroCuarto());
		docData.put("numeroCama", nota.getNumeroCama());
		docData.put("fechaNota", nota.getFechaNota());
		docData.put("horaNota", nota.getHoraNota());
		docData.put("observacion", nota.getObservacion());
		docData.put("tensionArterialSistolico", nota.getTensionArterialSistolico());
		docData.put("tensionArterialDiastolico", nota.getTensionArterialDiastolico());
		docData.put("tensionArterial", nota.getTensionArterial());
		docData.put("frecuenciaCardiaca", nota.getFrecuenciaCardiaca());
		docData.put("frecuenciaRespiratoria", nota.getFrecuenciaRespiratoria());
		docData.put("temperatura", nota.getTemperatura());
		docData.put("saturacion", nota.getSaturacion());
		docData.put("glucometria", nota.getGlucometria());
		docData.put("idEnfermera", nota.getIdEnfermera());
		return docData;
	}

}
