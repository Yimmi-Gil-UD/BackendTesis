package com.tesis.u.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import com.tesis.u.dto.LogRegistroDTO;
import com.tesis.u.entity.Fundacion;
import com.tesis.u.entity.LogRegistro;
import com.tesis.u.firebase.FirebaseConfig;
import com.tesis.u.service.LogRegistroService;

@Service
public class LogRegistroServiceImpl implements LogRegistroService {

	@Autowired
	private FirebaseConfig firebase;
	
		
	@Override
	public List<LogRegistroDTO> list() {
		List<LogRegistroDTO> response = new ArrayList();
		LogRegistroDTO log;

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				log = doc.toObject(LogRegistroDTO.class);
				log.setId(doc.getId());
				response.add(log);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public Boolean save(LogRegistro log) {
		Map<String, Object> docData = getDocData(log);

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
	
	private CollectionReference getCollection() {
		return firebase.getFirestore().collection("LogRegistro");
	}
	
	private Map<String, Object> getDocData(LogRegistro log) {
		
		LocalDate fechaLog = LocalDate.now();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		
		Map<String, Object> docData = new HashMap<>();
		docData.put("usuarioId", log.getUsuarioId());
		docData.put("accion", log.getAccion());
		docData.put("cambio", log.getCambio());
		//docData.put("fecha", fechaLog);
		//docData.put("fecha", log.getFecha());
		docData.put("fecha", date);
		docData.put("hora", dateFormat.format(date));
		//docData.put("hora", log.getHora());
		return docData;
		
	}

}
