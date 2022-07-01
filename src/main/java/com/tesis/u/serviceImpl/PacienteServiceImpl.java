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
import com.tesis.u.dto.CategoriaDiscapacidadDTO;
import com.tesis.u.dto.EstadoDTO;
import com.tesis.u.dto.FundacionDTO;
import com.tesis.u.dto.GeneroDTO;
import com.tesis.u.dto.GrupoSanguineoDTO;
import com.tesis.u.dto.PacienteDTO;
import com.tesis.u.dto.TipoDocumentoIdDTO;
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
		EstadoDTO estado;
		GeneroDTO genero;
		CategoriaDiscapacidadDTO categoriaDiscapacidad;
		GrupoSanguineoDTO grupoSanguineo;
		

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				paciente = doc.toObject(PacienteDTO.class);
				paciente.setId(doc.getId());
				
				DocumentReference datosEstado = firebase.getFirestore().collection("Estado").document(paciente.getIdEstadoPaciente());
				DocumentReference datosGenero = firebase.getFirestore().collection("Genero").document(paciente.getIdGenero());
				DocumentReference datosCategoriaDiscapacidad = firebase.getFirestore().collection("CategoriaDiscapacidad").document(paciente.getIdCategoriaDiscapacidad());
				DocumentReference datosGrupoSanguineo = firebase.getFirestore().collection("GrupoSanguineo").document(paciente.getIdGrupoSanguineo());
				
				ApiFuture<DocumentSnapshot> future2 = datosEstado.get();
				DocumentSnapshot document2 = future2.get();
				estado = document2.toObject(EstadoDTO.class);
				
				ApiFuture<DocumentSnapshot> futureGenero = datosGenero.get();
				DocumentSnapshot documentGenero = futureGenero.get();
				genero = documentGenero.toObject(GeneroDTO.class);
				
				ApiFuture<DocumentSnapshot> futureCategoriaDiscapacidad = datosCategoriaDiscapacidad.get();
				DocumentSnapshot documentCategoriaDiscapacidad = futureCategoriaDiscapacidad.get();
				categoriaDiscapacidad = documentCategoriaDiscapacidad.toObject(CategoriaDiscapacidadDTO.class);
				
				ApiFuture<DocumentSnapshot> futureGrupoSanguineo = datosGrupoSanguineo.get();
				DocumentSnapshot documentGrupoSanguineo = futureGrupoSanguineo.get();
				grupoSanguineo = documentGrupoSanguineo.toObject(GrupoSanguineoDTO.class);
				
								
				paciente.setDescripcionEstadoPaciente(estado.getDescripcionEstado());
				paciente.setNombreGenero(genero.getNombreGenero());
				paciente.setNombreCategoria(categoriaDiscapacidad.getNombreDiscapacidad());
				paciente.setNombreGrupo(grupoSanguineo.getNombreGrupo());
								
				response.add(paciente);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
	@Override
	public List<PacienteDTO> detail(String id) {
		List<PacienteDTO> response = new ArrayList();
		PacienteDTO paciente;
		CategoriaDiscapacidadDTO categoria;
		GrupoSanguineoDTO grupo;
		TipoDocumentoIdDTO tipo;
		GeneroDTO genero;
		EstadoDTO estado;
		
		try {
		DocumentReference datosPaciente = firebase.getFirestore().collection("Paciente") .document(id);
		ApiFuture<DocumentSnapshot> future = datosPaciente.get();
		DocumentSnapshot document = future.get();
		paciente = document.toObject(PacienteDTO.class);
		paciente.setId(document.getId());
		
		DocumentReference datosCategoria = firebase.getFirestore().collection("CategoriaDiscapacidad") .document(paciente.getIdCategoriaDiscapacidad());
		DocumentReference datosGrupo = firebase.getFirestore().collection("GrupoSanguineo").document(paciente.getIdGrupoSanguineo());
		DocumentReference datosDocumento = firebase.getFirestore().collection("TipoDocumentoId").document(paciente.getIdTipoDocumentoP());
		DocumentReference GeneroDocumento = firebase.getFirestore().collection("Genero").document(paciente.getIdGenero());
		DocumentReference EstadoDocumento = firebase.getFirestore().collection("Estado").document(paciente.getIdEstadoPaciente());
		
		
		ApiFuture<DocumentSnapshot> futureCategoria = datosCategoria.get();
		DocumentSnapshot documentCategoria = futureCategoria.get();
		categoria = documentCategoria.toObject(CategoriaDiscapacidadDTO.class);
		
		ApiFuture<DocumentSnapshot> futureGrupo = datosGrupo.get();
		DocumentSnapshot documentGrupo = futureGrupo.get();
		grupo = documentGrupo.toObject(GrupoSanguineoDTO.class);
		
		ApiFuture<DocumentSnapshot> futureDocumento = datosDocumento.get();
		DocumentSnapshot documentDocumento = futureDocumento.get();
		tipo = documentDocumento.toObject(TipoDocumentoIdDTO.class);
		
		ApiFuture<DocumentSnapshot> futureGenero = GeneroDocumento.get();
		DocumentSnapshot documentGenero = futureGenero.get();
		genero = documentGenero.toObject(GeneroDTO.class);
		
		ApiFuture<DocumentSnapshot> futureEstado = EstadoDocumento.get();
		DocumentSnapshot documentEstado = futureEstado.get();
		estado = documentEstado.toObject(EstadoDTO.class);
		
		paciente.setNombreGenero(genero.getNombreGenero());
		paciente.setNombreCategoria(categoria.getNombreDiscapacidad());
		paciente.setNombreGrupo(grupo.getNombreGrupo());
		paciente.setTipoDocumento(tipo.getDescripcionDocumento());
		paciente.setDescripcionEstadoPaciente(estado.getDescripcionEstado());
		
		response.add(paciente);
		
		
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
		docData.put("numeroIdentificacionP", paciente.getNumeroIdentificacionP());
		docData.put("idTipoDocumentoP", paciente.getIdTipoDocumentoP());
		docData.put("fechaNacimiento", paciente.getFechaNacimiento());
		docData.put("direccion", paciente.getDireccion());
		docData.put("telefono", paciente.getTelefono());
		docData.put("idGenero", paciente.getIdGenero());
		docData.put("idCategoriaDiscapacidad", paciente.getIdCategoriaDiscapacidad());
		docData.put("idGrupoSanguineo", paciente.getIdGrupoSanguineo());
		docData.put("idEstadoPaciente", paciente.getIdEstadoPaciente());
		return docData;
	}


	
	
}
