package com.lt.consants;


public enum Role {

	Student("student"),
	Professor("professor"),
	Admin("admin");
	
	private final String role;
	 Role(String role){
		this.role = role;
	}
	 
	 public Role getRole(String role) {
		 for (Role e : values()) {
		        if (e.role.equals(role)) {
		            return e;
		        }
		    }
		    return null;
	 }
}
