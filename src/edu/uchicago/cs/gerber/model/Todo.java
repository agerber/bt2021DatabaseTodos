package edu.uchicago.cs.gerber.model;

import java.io.Serializable;

public class Todo implements Serializable {
	private long lId;
	private String strTitle;
	private String strDetail;

	
	public Todo(long lId, String strTitle, String strDetail) {

		this.lId = lId;
		this.strTitle = strTitle;
		this.strDetail = strDetail;
	}
	
	public Todo( String strTitle, String strDetail) {

		this.strTitle = strTitle;
		this.strDetail = strDetail;
	}
	
	public Todo(){};

	public long getId() {
		return lId;
	}

	public void setId(long lId) {
		this.lId = lId;
	}

	public String getTitle() {
		return strTitle;
	}

	public void setTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	
	public String getDetail() {
		return strDetail;
	}

	public void setDetail(String strDetail) {
		this.strDetail = strDetail;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		String strLine = "";
		if (!strDetail.isEmpty())
			strLine = strTitle + " : " + strDetail;
		else
			strLine = strTitle;
		
		
			
		if (strLine.length() > 30){
			return strLine.substring(0, 30) + "...";
		} else {
			return strLine;
		}
		
		
	}
} 