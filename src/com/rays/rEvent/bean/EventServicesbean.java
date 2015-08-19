package com.rays.rEvent.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class EventServicesbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("d")
	public ArrayList<GetEventServices> GetEventServices = new ArrayList<GetEventServices>();

	public class GetEventServices implements Serializable {
		private static final long serialVersionUID = 1L;
		@SerializedName("REG_ID")
		public String REG_ID;
		@SerializedName("REG_TITLE")
		public String REG_TITLE;
		@SerializedName("REG_FIRST_NAME")
		public String REG_FIRST_NAME;
		@SerializedName("REG_LAST_NAME")
		public String REG_LAST_NAME;
		@SerializedName("RESULTSERVICES")
		public ArrayList<GetServicesTaken> GetEventServicesTaken = new ArrayList<GetServicesTaken>();

	}

	public class GetServicesTaken implements Serializable {
		private static final long serialVersionUID = 1L;
		@SerializedName("REG_NAME")
		public String REG_NAME;
		@SerializedName("SERVICE_NAME")
		public String SERVICE_NAME;
		@SerializedName("END_DATE")
		public String END_DATE;
		@SerializedName("START_DATE")
		public String START_DATE;
		@SerializedName("START_TIME")
		public String START_TIME;
		@SerializedName("END_TIME")
		public String END_TIME;
		@SerializedName("Flag")
		public String Flag;
		@SerializedName("Mesg")
		public String Mesg;
	}
}
