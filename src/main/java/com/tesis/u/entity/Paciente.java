package com.tesis.u.entity;

import java.util.Date;

public class Paciente {
	
	private String nombrePaciente;
	private String apellidoPaciente;
	private long numeroIdentificacionP;
	private String idTipoDocumentoP;
	private Date fechaNacimiento;
	private String direccion;
	private long telefono;
	private String idGenero;
	private String idCategoriaDiscapacidad;
	private String idGrupoSanguineo;
	
	
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	public String getApellidoPaciente() {
		return apellidoPaciente;
	}
	public void setApellidoPaciente(String apellidoPaciente) {
		this.apellidoPaciente = apellidoPaciente;
	}
	public long getNumeroIdentificacionP() {
		return numeroIdentificacionP;
	}
	public void setNumeroIdentificacionP(long numeroIdentificacionP) {
		this.numeroIdentificacionP = numeroIdentificacionP;
	}
	public String getIdTipoDocumentoP() {
		return idTipoDocumentoP;
	}
	public void setIdTipoDocumentoP(String idTipoDocumentoP) {
		this.idTipoDocumentoP = idTipoDocumentoP;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	public String getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}
	public String getIdCategoriaDiscapacidad() {
		return idCategoriaDiscapacidad;
	}
	public void setIdCategoriaDiscapacidad(String idCategoriaDiscapacidad) {
		this.idCategoriaDiscapacidad = idCategoriaDiscapacidad;
	}
	public String getIdGrupoSanguineo() {
		return idGrupoSanguineo;
	}
	public void setIdGrupoSanguineo(String idGrupoSanguineo) {
		this.idGrupoSanguineo = idGrupoSanguineo;
	}
	

}
