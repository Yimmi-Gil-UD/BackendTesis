package com.tesis.u.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.tesis.u.dto.EnfermeraDTO;
import com.tesis.u.dto.NotaEnfermeriaDTO;
import com.tesis.u.dto.NotaTerapiaDTO;
import com.tesis.u.dto.PacienteDTO;
import com.tesis.u.dto.TipoTerapiaDTO;
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
		PacienteDTO paciente;
		EnfermeraDTO enfermera;

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
		
		

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				nota = doc.toObject(NotaEnfermeriaDTO.class);
				nota.setId(doc.getId());
						
			
				DocumentReference datosPaciente = firebase.getFirestore().collection("Paciente") .document(nota.getIdPaciente());
				DocumentReference datosEnfermera = firebase.getFirestore().collection("Enfermera").document(nota.getIdEnfermera());
				
				ApiFuture<DocumentSnapshot> future = datosPaciente.get();
				DocumentSnapshot document = future.get();
				paciente = document.toObject(PacienteDTO.class);
				
				ApiFuture<DocumentSnapshot> future2 = datosEnfermera.get();
				DocumentSnapshot document2 = future2.get();
				enfermera = document2.toObject(EnfermeraDTO.class);
				
				 
							
				nota.setNombrePaciente(paciente.getNombrePaciente());
				nota.setApellidoPaciente(paciente.getApellidoPaciente());
				nota.setNombreEnfermera(enfermera.getNombre());
				nota.setApellidoEnfermera(enfermera.getApellido());
				response.add(nota);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	@Override
	public List<NotaEnfermeriaDTO> detail(String id) {
		List<NotaEnfermeriaDTO> response = new ArrayList();
		NotaEnfermeriaDTO nota;
		PacienteDTO paciente;
		EnfermeraDTO enfermera;
		
		try {
			DocumentReference datosNotaEnfermeria = firebase.getFirestore().collection("NotaEnfermeria") .document(id);
			ApiFuture<DocumentSnapshot> future = datosNotaEnfermeria.get();
			DocumentSnapshot document = future.get();
			nota = document.toObject(NotaEnfermeriaDTO.class);
			nota.setId(document.getId());
			
			DocumentReference datosPaciente = firebase.getFirestore().collection("Paciente") .document(nota.getIdPaciente());
			DocumentReference datosEnfermera = firebase.getFirestore().collection("Enfermera").document(nota.getIdEnfermera());
			
						
			ApiFuture<DocumentSnapshot> futurePaciente = datosPaciente.get();
			DocumentSnapshot documentPaciente = futurePaciente.get();
			paciente = documentPaciente.toObject(PacienteDTO.class);
			
			ApiFuture<DocumentSnapshot> futureEnfermera = datosEnfermera.get();
			DocumentSnapshot documentEnfermera = futureEnfermera.get();
			enfermera = documentEnfermera.toObject(EnfermeraDTO.class);
			
			
			nota.setNombrePaciente(paciente.getNombrePaciente());
			nota.setApellidoPaciente(paciente.getApellidoPaciente());
			nota.setDocumentoPaciente(paciente.getNumeroIdentificacionP());
			nota.setNombreEnfermera(enfermera.getNombre());
			nota.setApellidoEnfermera(enfermera.getApellido());
			nota.setDocumentoEnfermera(enfermera.getNumeroIdentificacion());
			
			response.add(nota);
			
				return response;
			} catch (Exception e) {
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
