package com.vuclip.smpp.talend.retrail.orm.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "smpp_data")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "smpp_data")
public class SmppData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "msisdn")
	private String msisdn;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "req_status")
	private String reqStatus;

	@Column(name = "resp_status")
	private String respStatus;

	@Column(name = "message_id")
	private String messageId;

	@Column(name = "price_point")
	private String pricePoint;

	@Column(name = "created_date", updatable = false, insertable = false)
	private String createdDate;

	@Column(name = "modified_date", updatable = false, insertable = false)
	private String modifiedDate;

	@Column(name = "dn_message")
	private String dnMessage;

	@Column(name = "dlr_url")
	private String dlrURL;

	@Column(name = "retries")
	private int reties;

	@Column(name = "talend_response")
	private String talendResponse;

	public SmppData() {
	}

	public SmppData(int id, String msisdn, String transactionId, String reqStatus, String respStatus, String messageId,
			String pricePoint, String createdDate, String modifiedDate, String dnMessage, String dlrURL, int retries,
			String talendResponse) {
		super();
		this.id = id;
		this.msisdn = msisdn;
		this.transactionId = transactionId;
		this.reqStatus = reqStatus;
		this.respStatus = respStatus;
		this.messageId = messageId;
		this.pricePoint = pricePoint;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.dnMessage = dnMessage;
		this.dlrURL = dlrURL;
		this.reties = retries;
		this.talendResponse = talendResponse;
	}

	public SmppData(String msisdn, String transactionId, String reqStatus, String respStatus, String messageId,
			String pricePoint, String createdDate, String modifiedDate, String dnMessage, String dlrURL, int retries,
			String talendResponse) {
		super();
		this.msisdn = msisdn;
		this.transactionId = transactionId;
		this.reqStatus = reqStatus;
		this.respStatus = respStatus;
		this.messageId = messageId;
		this.pricePoint = pricePoint;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.dnMessage = dnMessage;
		this.dlrURL = dlrURL;
		this.reties = retries;
		this.talendResponse = talendResponse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	public String getRespStatus() {
		return respStatus;
	}

	public void setRespStatus(String respStatus) {
		this.respStatus = respStatus;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getPricePoint() {
		return pricePoint;
	}

	public void setPricePoint(String pricePoint) {
		this.pricePoint = pricePoint;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getDnMessage() {
		return dnMessage;
	}

	public void setDnMessage(String dnMessage) {
		this.dnMessage = dnMessage;
	}

	public String getDlrURL() {
		return dlrURL;
	}

	public void setDlrURL(String dlrURL) {
		this.dlrURL = dlrURL;
	}

	public int getReties() {
		return reties;
	}

	public void setReties(int reties) {
		this.reties = reties;
	}

	public String getTalendResponse() {
		return talendResponse;
	}

	public void setTalendResponse(String talendResponse) {
		this.talendResponse = talendResponse;
	}

	@Override
	public String toString() {
		return new StringBuilder("SmppData [id=").append(id).append(", msisdn=").append(msisdn)
				.append(", transactionId=").append(transactionId).append(", messageId=").append(messageId)
				.append(", pricePoint=").append(pricePoint).append(", createdDate=").append(createdDate)
				.append(", modifiedDate=").append(modifiedDate).append(", DN Message=").append(dnMessage)
				.append(", dlrURL=").append(dlrURL).append(", Talend Response=").append(talendResponse).append("]")
				.toString();
	}

}
