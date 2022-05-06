package com.tesis.u.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.database.Query;
import com.tesis.u.dto.EmpleadoDTO;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private FirebaseConfig firebase;

	@Override
	public List<EmpleadoDTO> list() {

		List<EmpleadoDTO> response = new ArrayList();
		EmpleadoDTO empleado;

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				empleado = doc.toObject(EmpleadoDTO.class);
				empleado.setId(doc.getId());
				response.add(empleado);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public Boolean save(EmpleadoDTO empleado) {

		Map<String, Object> docData = getDocData(empleado);
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
	public Boolean update(String id, EmpleadoDTO empleado) {
		Map<String, Object> docData = getDocData(empleado);
		ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).set(docData);

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
	public Boolean delete(String id) {
		ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();

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
	

	private Map<String, Object> getDocData(EmpleadoDTO empleado) {
		Map<String, Object> docData = new HashMap<>();

		docData.put("idRol", empleado.getIdRol());
		docData.put("numeroIdentificacion", empleado.getNumeroIdentificacion());
		return docData;
	}

	private CollectionReference getCollection() {
		return firebase.getFirestore().collection("Empleado");
	}

}
