package com.ibm.lnw.backend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jan Valentik on 11/15/2015.
 */
@NamedQueries({
		@NamedQuery(name="Request.findAll",
					query="SELECT r FROM Request r"),
		@NamedQuery(name="Request.findAllByUser",
					query="SELECT r FROM Request r WHERE r.submitterUserName=:filter"),
		@NamedQuery(name="Request.findAllByUserAndFilter",
					query = "SELECT r FROM Request r WHERE r.submitterUserName=:filter1 AND (LOWER(r.leadingWBS) LIKE" +
							" :filter2 OR LOWER(r.customerName) LIKE :filter2)"),
		@NamedQuery(name="Request.findByID", query = "SELECT r FROM Request r WHERE r.id=:filter"),
        @NamedQuery(name="Request.findByFilter", query = "SELECT r FROM Request r WHERE LOWER(r.leadingWBS) LIKE " +
                ":filter OR LOWER(r.customerName) LIKE :filter")
})
@Entity
public class Request implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Pattern(regexp = "([a-zA-Z]\\.)+(\\w{5}\\.)+(\\d{3})", message = "WBS must be in format x.xxxxx.xxx")
	private String leadingWBS;
	@NotNull
	private String customerName;
	@NotNull
	private String contractNumber;

	private String services;
	@NotNull
	private String pmaName;
	private String pexName;
	private String comments;
	private String submitterUserName;

    @Temporal(TemporalType.DATE)
	private Date dateTimeStamp;
	private RequestStatus status;

	public Request() {
		customerName = contractNumber = services = pmaName = pexName = comments = submitterUserName = leadingWBS ="";
		dateTimeStamp = new Date();
		status = RequestStatus.Open;
	}

    public String getSubmitterUserName() {
		return submitterUserName;
	}

	public void setSubmitterUserName(String submitterUserName) {
		this.submitterUserName = submitterUserName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName.trim();
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber.trim();
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services.trim();
	}

	public String getPmaName() {
		return pmaName;
	}

	public void setPmaName(String pmaName) {
		this.pmaName = pmaName.trim();
	}

	public String getPexName() {
		return pexName;
	}

	public void setPexName(String pexName) {
		this.pexName = pexName.trim();
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments.trim();
	}

	public Date getDateTimeStamp() {
		return dateTimeStamp;
	}

	public String getLeadingWBS() {
		return leadingWBS;
	}

	public void setLeadingWBS(String leadingWBS) {
		this.leadingWBS = leadingWBS.trim();
	}

	public int getId() {
		return id;
	}

    public void setId(int id) {
        this.id = id;
    }

    public void setDateTimeStamp(Date dateTimeStamp) {
        this.dateTimeStamp = dateTimeStamp;
    }

    public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}
}
