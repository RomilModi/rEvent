package com.rays.rEvent.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class BadgePDF implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@SerializedName("d")
	public ArrayList<GetBadgePDFDetails> GetBadgePDFDetails = new ArrayList<GetBadgePDFDetails>();

	public class GetBadgePDFDetails implements Serializable {
		private static final long serialVersionUID = 1L;
		@SerializedName("CONTENT")
		public String CONTENT;
		@SerializedName("Flag")
		public String Flag;
		@SerializedName("Mesg")
		public String Mesg;

	}
}
