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
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.tesis.u.MainSecurity;
import com.tesis.u.dto.CategoriaDiscapacidadDTO;
import com.tesis.u.dto.EnfermeraDTO;
import com.tesis.u.dto.EstadoDTO;
import com.tesis.u.dto.FundacionDTO;
import com.tesis.u.dto.GeneroDTO;
import com.tesis.u.dto.GrupoSanguineoDTO;
import com.tesis.u.dto.PacienteDTO;
import com.tesis.u.dto.RolDTO;
import com.tesis.u.dto.TipoDocumentoIdDTO;
import com.tesis.u.entity.Enfermera;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.EnfermeraService;

@Service
public class EnfermeraServiceImpl implements EnfermeraService{

	@Autowired
	private FirebaseConfig firebase;
	
	//@Autowired
	private MainSecurity security;
	
	@Override
	public List<EnfermeraDTO> list() {
		List<EnfermeraDTO> response = new ArrayList();
		EnfermeraDTO enfermera;
		EstadoDTO estado;

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				enfermera = doc.toObject(EnfermeraDTO.class);
				enfermera.setId(doc.getId());
				
				DocumentReference datosEstado = firebase.getFirestore().collection("Estado").document(enfermera.getIdEstadoEnfermera());
				
				ApiFuture<DocumentSnapshot> future2 = datosEstado.get();
				DocumentSnapshot document2 = future2.get();
				estado = document2.toObject(EstadoDTO.class);
				
				enfermera.setDescripcionEstadoEnfermera(estado.getDescripcionEstado());
				
				response.add(enfermera);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
	@Override
	public List<EnfermeraDTO> detail(String id) {
		List<EnfermeraDTO> response = new ArrayList();
		EnfermeraDTO enfermera;
		TipoDocumentoIdDTO documento;
		GeneroDTO genero;
		RolDTO rol;
		EstadoDTO estado;
		
		
		
		
		try {
			DocumentReference datosEnfermera = firebase.getFirestore().collection("Enfermera") .document(id);
			ApiFuture<DocumentSnapshot> future = datosEnfermera.get();
			DocumentSnapshot document = future.get();
			enfermera = document.toObject(EnfermeraDTO.class);
			enfermera.setId(document.getId());
			
			DocumentReference datosDocumento = firebase.getFirestore().collection("TipoDocumentoId") .document(enfermera.getIdTipoDocumentoE());
			DocumentReference datosGenero = firebase.getFirestore().collection("Genero").document(enfermera.getIdGenero());
			DocumentReference datosRol = firebase.getFirestore().collection("Rol").document(enfermera.getIdRol());
			DocumentReference datosEstado = firebase.getFirestore().collection("Estado").document(enfermera.getIdEstadoEnfermera());
			
			
			
			ApiFuture<DocumentSnapshot> futureDocumento = datosDocumento.get();
			DocumentSnapshot documentDocumento = futureDocumento.get();
			documento = documentDocumento.toObject(TipoDocumentoIdDTO.class);
			
			ApiFuture<DocumentSnapshot> futureGenero = datosGenero.get();
			DocumentSnapshot documentGenero = futureGenero.get();
			genero = documentGenero.toObject(GeneroDTO.class);
			
			ApiFuture<DocumentSnapshot> futureRol = datosRol.get();
			DocumentSnapshot documentRol = futureRol.get();
			rol = documentRol.toObject(RolDTO.class);
			
			ApiFuture<DocumentSnapshot> futureEstado = datosEstado.get();
			DocumentSnapshot documentEstado = futureEstado.get();
			estado = documentEstado.toObject(EstadoDTO.class);
			
			
			enfermera.setTipoDocumento(documento.getDescripcionDocumento());
			enfermera.setDescripcionGenero(genero.getNombreGenero());
			enfermera.setDescripcionRol(rol.getNombreRol());
			enfermera.setDescripcionEstadoEnfermera(estado.getDescripcionEstado());
			
			response.add(enfermera);
			
			
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
		docData.put("idTipoDocumentoE", enfermera.getIdTipoDocumentoE());
		docData.put("numeroIdentificacion", enfermera.getNumeroIdentificacion());
		docData.put("correo", enfermera.getCorreo());
		//docData.put("password",passwordEncoder.encode(enfermera.getPassword()));
		//docData.put("password",enfermera.getPassword());
		docData.put("password",this.security.encrypt(enfermera.getPassword()));
		docData.put("idGenero", enfermera.getIdGenero());
		docData.put("idRol", enfermera.getIdRol());
		docData.put("idEstadoEnfermera", enfermera.getIdEstadoEnfermera());
		return docData;
	}



}
