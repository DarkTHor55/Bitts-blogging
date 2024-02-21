package com.Bitts.excepction;

public class ResourceNotFound extends RuntimeException {
	String resource;
	String fieldname;
	Long Field;
	public ResourceNotFound(String resource, String fieldname, Long field) {
		super(String.format("%s not found with %s : %l",resource,fieldname,field ));
		this.resource = resource;
		this.fieldname = fieldname;
		Field = field;
	}
	

}
