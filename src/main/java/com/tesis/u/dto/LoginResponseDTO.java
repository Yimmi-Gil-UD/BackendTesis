package com.tesis.u.dto;

public class LoginResponseDTO {
	

	private String id;
	private String correo;
	private String rol;
	
		public LoginResponseDTO(String correo, String rol, String id) {
		this.correo = correo;
		this.rol = rol;
		this.id = id;
	}
	
	
	public LoginResponseDTO() {

	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
