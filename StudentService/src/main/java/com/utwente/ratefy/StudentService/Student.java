package com.utwente.ratefy.StudentService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Column(name = "student_id")
    private Integer studentID;
	@Column(name = "name")
    private String name;
	@Column(name = "email")
    private String email;
	@Column(name = "opt_in")
    private boolean optIn;
	@Column(name = "points")
    private Integer points;
	@Column(name = "authToken")
    private String authToken;
	
	

	/**
	   * @param id
	   * @param studentID
	   * @param name
	   * @param email
	   * @param optIn
	   * @param points
	   * @param authToken
	*/
	Student(Long id, Integer studentID, String name, String email, boolean optIn,Integer points,String authToken ) {
		    this.id = id;
		    this.studentID = studentID;
		    this.name = name;
		    this.email = email;
		    this.optIn = optIn;
		    this.points = points;
		    this.authToken = authToken;
	}
	Student(){}
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getStudentID() {
		return studentID;
	}
	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
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
	public Boolean getOptIn() {
		return optIn;
	}
	public void setOptIn(Boolean optIn) {
		this.optIn = optIn;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	@Override
	  public String toString() {
	    final StringBuilder sb = new StringBuilder("Questionnaire {");
	    sb.append("studentId=").append(studentID);
	    sb.append(", name='").append(name).append('\'');
	    sb.append(", email=").append(email).append('\'');
	    ;
	    sb.append(", points=").append(points).append('\'');
	    ;
	    sb.append('}');
	    return sb.toString();
	  }
}
