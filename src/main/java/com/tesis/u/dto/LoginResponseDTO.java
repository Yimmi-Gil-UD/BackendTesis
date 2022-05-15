package com.tesis.u.dto;

public class LoginResponseDTO {
	

	private String idUsuario;
	private String correo;
	//private String rol;
	
	
	
	
	public LoginResponseDTO(String correo,/* String rol,*/ String idUsuario) {
		this.correo = correo;
		//this.rol = rol;
		this.idUsuario = idUsuario;
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/*public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}*/
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	

}
