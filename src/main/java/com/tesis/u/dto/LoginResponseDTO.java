package com.tesis.u.dto;

public class LoginResponseDTO {
	

	private String id;
	private String correo;
	private String rol;
	
		public LoginResponseDTO(String id,String correo, String rol) {
		this.id = id;
		this.correo = correo;
		this.rol = rol;
		
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
