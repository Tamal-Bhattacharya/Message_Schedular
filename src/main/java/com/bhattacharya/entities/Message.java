package com.bhattacharya.entities;

import java.sql.Timestamp;

public class Message {
	private int Message_ID;
	private int Account_ID;
	private String msg;
	private Timestamp timestamp;
	private String Send_To;
	private int Status;

	public int getMessage_ID() {
		return Message_ID;
	}
	public void setMessage_ID(int message_ID) {
		Message_ID = message_ID;
	}
	public int getAccount_ID() {
		return Account_ID;
	}
	public void setAccount_ID(int account_ID) {
		Account_ID = account_ID;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getSend_To() {
		return Send_To;
	}
	public void setSend_To(String send_To) {
		Send_To = send_To;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	
	
}
