package com.utwente.ratefy.UserService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Column(name = "user_id")
    private Integer userID;
	@Column(name = "name")
    private String name;
	@Column(name = "email")
    private String email;
	@Column(name = "permission")
    private Integer permission;

	
	User(Long id, Integer userID, String name, String email, Integer permission) {
		    this.id = id;
		    this.userID = userID;
		    this.name = name;
		    this.email = email;
		    this.permission = permission;
	}
	User(){}
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPermission() {
		return permission;
	}
	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	@Override
	  public String toString() {
	    final StringBuilder sb = new StringBuilder("Questionnaire {");
	    sb.append("userId=").append(userID);
	    sb.append(", name='").append(name).append('\'');
	    sb.append(", email=").append(email).append('\'');
	    ;
	    sb.append(", permission=").append(permission).append('\'');
	    ;
	    sb.append('}');
	    return sb.toString();
	  }
}
