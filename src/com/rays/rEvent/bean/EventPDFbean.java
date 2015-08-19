package com.rays.rEvent.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class EventPDFbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("d")
	public ArrayList<GetEventPDF> GetLoginDetails = new ArrayList<GetEventPDF>();

	public class GetEventPDF implements Serializable {
		private static final long serialVersionUID = 1L;
		@SerializedName("URL")
		public String URL;
		@SerializedName("Flag")
		public String Flag;
		@SerializedName("Mesg")
		public String Mesg;

	}

}
