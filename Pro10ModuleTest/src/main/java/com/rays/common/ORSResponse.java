package com.rays.common;

import java.util.HashMap;
import java.util.Map;

public class ORSResponse {
	
	public static String DATA="data";
	public static String MESSAGE="message";
	public static String INPUT_ERROR="inputerror";
	
	public boolean sucess=false;
	
	Map<String , Object> result=new HashMap<String, Object>();
	
	public ORSResponse() {
		
	}
	
	public ORSResponse (boolean success) {
		this.sucess=success;
				
		
	}
	
	
	public void addResult(String key,Object value) {
		result.put(key, value);
		
		
		}
	
	public void addData(Object value) {
		result.put(DATA, value);
		
	}
	
	public void addMessage(Object value) {
		result.put(MESSAGE, value);
		
	}
	
	public void addInputErrors(Object value) {
		result.put(INPUT_ERROR, value);
	}

	public boolean isSucess() {
		return sucess;
	}

	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
	
	

	

}
