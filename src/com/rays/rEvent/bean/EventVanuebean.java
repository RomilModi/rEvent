package com.rays.rEvent.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class EventVanuebean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("d")
	public ArrayList<GetEventVanue> GetEventVanue = new ArrayList<GetEventVanue>();

	public class GetEventVanue implements Serializable {
		private static final long serialVersionUID = 1L;
		@SerializedName("HEADER_CONTENT")
		public String HEADER_CONTENT;
		@SerializedName("FOOTER_CONTENT")
		public String FOOTER_CONTENT;
		@SerializedName("EVT_ID")
		public String EVT_ID;
		@SerializedName("EVT_NAME")
		public String EVT_NAME;
		@SerializedName("EVT_SHORT_NAME")
		public String EVT_SHORT_NAME;
		@SerializedName("EVT_START_DATE")
		public String EVT_START_DATE;
		@SerializedName("EVT_END_DATE")
		public String EVT_END_DATE;
		@SerializedName("EVT_START_TIME")
		public String EVT_START_TIME;
		@SerializedName("EVT_END_TIME")
		public String EVT_END_TIME;
		@SerializedName("EVT_VENUE")
		public String EVT_VENUE;
		@SerializedName("EVT_CITY")
		public String EVT_CITY;
		@SerializedName("EVT_STATE")
		public String EVT_STATE;
		@SerializedName("EVT_WEB_URL")
		public String EVT_WEB_URL;
		@SerializedName("EVT_EMAIL_ADDRESS")
		public String EVT_EMAIL_ADDRESS;
		@SerializedName("EVT_IS_CURRENT")
		public String EVT_IS_CURRENT;
		@SerializedName("EVT_COUNTRY")
		public String EVT_COUNTRY;
		@SerializedName("EVT_CONTACT_PERSON")
		public String EVT_CONTACT_PERSON;
		@SerializedName("EVT_CONTACT_NUMBER")
		public String EVT_CONTACT_NUMBER;
		@SerializedName("EVT_ZIP_CODE")
		public String EVT_ZIP_CODE;
		@SerializedName("EVT_LOGO_PATH")
		public String EVT_LOGO_PATH;
		@SerializedName("EVT_BADGE_ALLOW")
		public String EVT_BADGE_ALLOW;
		@SerializedName("EVT_BADGE_FORMAT")
		public String EVT_BADGE_FORMAT;
		@SerializedName("EVT_CREATED_USER_ID")
		public String EVT_CREATED_USER_ID;
		@SerializedName("EVT_CREATED_DATE")
		public String EVT_CREATED_DATE;
		@SerializedName("EVT_LAST_CHANGED_USER_ID")
		public String EVT_LAST_CHANGED_USER_ID;
		@SerializedName("EVT_LAST_CHANGED_DATE")
		public String EVT_LAST_CHANGED_DATE;
		@SerializedName("EVT_RECORD_STATUS_FLAG")
		public String EVT_RECORD_STATUS_FLAG;
		@SerializedName("EVT_RECORD_STATUS_DATE")
		public String EVT_RECORD_STATUS_DATE;
		@SerializedName("EVT_TRANSACTION_GUID")
		public String EVT_TRANSACTION_GUID;
		@SerializedName("EVT_RECORD_TIMESTAMP")
		public String EVT_RECORD_TIMESTAMP;
		@SerializedName("EVT_QC_FLAG_DONE")
		public String EVT_QC_FLAG_DONE;
		@SerializedName("REG_ID")
		public String REG_ID;
		@SerializedName("BADGE_NO")
		public String BADGE_NO;
		@SerializedName("Flag")
		public String Flag;
		@SerializedName("Mesg")
		public String Mesg;
	}

}
