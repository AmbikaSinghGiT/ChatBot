package com.example.chatbot.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;



@Entity

@Table
public class ChatbotModel {
	
	

	public ChatbotModel(Long id, Long applicationId, String name, String cropName, String state, String district,
			String taluka, String village, Long surveyNumber, String cause, Date date) {
		super();
		this.id = id;
		this.applicationId = applicationId;
		this.name = name;
		this.cropName = cropName;
		this.state = state;
		this.district = district;
		this.taluka = taluka;
		this.village = village;
		this.surveyNumber = surveyNumber;
		this.cause = cause;
		this.date = date;
	}

	public ChatbotModel() {
	//	super();
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	
	private Long id;
	
	@Column
    private Long applicationId;
    
    private String name;
    
    private String cropName;
    
    private String state;
    
    private String district;
    
    private String taluka;
    
    private String village;
    
    private Long surveyNumber;
    
    private String cause;
    
    private Date date;
//	@Column
//	private Long customer_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTaluka() {
		return taluka;
	}

	public void setTaluka(String taluka) {
		this.taluka = taluka;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public Long getSurveyNumber() {
		return surveyNumber;
	}

	public void setSurveyNumber(Long surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
//	@OneToMany(cascade = CascadeType.ALL)
	// @JoinColumn(name = "fk_id",referencedColumnName = "id")
	// private List<addCustomer> addcustomer;

	
	
	
}