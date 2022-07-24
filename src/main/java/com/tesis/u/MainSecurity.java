package com.tesis.u;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainSecurity {
	
	public static String encrypt(String pass) {
	    MessageDigest md = null;

	    byte[] passByte = null;


	    try {
	        passByte = pass.getBytes("UTF-8");
	        md = MessageDigest.getInstance("MD5");
	        
	        

	    } catch (NoSuchAlgorithmException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    byte[] converPass = md.digest(passByte);
	    
	    StringBuffer sb = new StringBuffer();
	    for (final byte b : converPass) {
	        sb.append(String.format("%02x", b));
	    }
	    String passCifrado = sb.toString().toUpperCase();

	    //String passCifrado = converPass.toString();

	    return passCifrado;
	}
	

}
