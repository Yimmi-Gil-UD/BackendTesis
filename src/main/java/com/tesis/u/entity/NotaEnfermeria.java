package com.tesis.u.entity;


import java.util.Date;

public class NotaEnfermeria {
	
	private String idPaciente;
	private int numeroCuarto;
	private int numeroCama;
	private Date fechaNota;
	private String horaNota;
	private String observacion;
	private int tensionArterialSistolico;
	private int tensionArterialDiastolico;
	private String tensionArterial;
	private int frecuenciaCardiaca;
	private int frecuenciaRespiratoria;
	private double temperatura;
	private int saturacion;
	private int glucometria;
	private String idEnfermera;
	
	
	public String getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getNumeroCuarto() {
		return numeroCuarto;
	}
	public void setNumeroCuarto(int numeroCuarto) {
		this.numeroCuarto = numeroCuarto;
	}
	public int getNumeroCama() {
		return numeroCama;
	}
	public void setNumeroCama(int numeroCama) {
		this.numeroCama = numeroCama;
	}
	public Date getFechaNota() {
		return fechaNota;
	}
	public void setFechaNota(Date fechaNota) {
		this.fechaNota = fechaNota;
	}
	public String getHoraNota() {
		return horaNota;
	}
	public void setHoraNota(String horaNota) {
		this.horaNota = horaNota;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public int getTensionArterialSistolico() {
		return tensionArterialSistolico;
	}
	public void setTensionArterialSistolico(int tensionArterialSistolico) {
		this.tensionArterialSistolico = tensionArterialSistolico;
	}
	public int getTensionArterialDiastolico() {
		return tensionArterialDiastolico;
	}
	public void setTensionArterialDiastolico(int tensionArterialDiastolico) {
		this.tensionArterialDiastolico = tensionArterialDiastolico;
	}
	public String getTensionArterial() {
		return tensionArterial;
	}
	public void setTensionArterial(String tensionArterial) {
		this.tensionArterial = tensionArterial;
	}
	public int getFrecuenciaCardiaca() {
		return frecuenciaCardiaca;
	}
	public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
		this.frecuenciaCardiaca = frecuenciaCardiaca;
	}
	public int getFrecuenciaRespiratoria() {
		return frecuenciaRespiratoria;
	}
	public void setFrecuenciaRespiratoria(int frecuenciaRespiratoria) {
		this.frecuenciaRespiratoria = frecuenciaRespiratoria;
	}
	public double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	public int getSaturacion() {
		return saturacion;
	}
	public void setSaturacion(int saturacion) {
		this.saturacion = saturacion;
	}
	public int getGlucometria() {
		return glucometria;
	}
	public void setGlucometria(int glucometria) {
		this.glucometria = glucometria;
	}
	public String getIdEnfermera() {
		return idEnfermera;
	}
	public void setIdEnfermera(String idEnfermera) {
		this.idEnfermera = idEnfermera;
	}
	
	
	
	
	
	

}