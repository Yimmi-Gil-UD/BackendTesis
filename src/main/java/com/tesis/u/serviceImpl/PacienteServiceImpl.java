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
import com.tesis.u.dto.PacienteDTO;
import com.tesis.u.entity.Paciente;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService{

	@Autowired
	private FirebaseConfig firebase;

	@Override
	public List<PacienteDTO> list() {
		List<PacienteDTO> response = new ArrayList();
		PacienteDTO paciente;

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				paciente = doc.toObject(PacienteDTO.class);
				paciente.setId(doc.getId());
				response.add(paciente);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public Boolean save(Paciente paciente) {
		Map<String, Object> docData = getDocData(paciente);

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
	public Boolean update(String id, Paciente paciente) {
		Map<String, Object> docData = getDocData(paciente);
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
		return firebase.getFirestore().collection("Paciente");
	}
	
	private Map<String, Object> getDocData(Paciente paciente) {
		Map<String, Object> docData = new HashMap<>();
		docData.put("nombrePaciente", paciente.getNombrePaciente());
		docData.put("apellidoPaciente", paciente.getApellidoPaciente());
		docData.put("fechaNacimiento", paciente.getFechaNacimiento());
		docData.put("direccion", paciente.getDireccion());
		docData.put("telefono", paciente.getTelefono());
		docData.put("idGenero", paciente.getIdGenero());
		docData.put("idCategoriaDiscapacidad", paciente.getIdCategoriaDiscapacidad());
		docData.put("idGrupoSanguineo", paciente.getIdGrupoSanguineo());
		docData.put("idFundacion", paciente.getIdFundacion());
		return docData;
	}
	
	
}
