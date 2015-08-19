package com.rays.rEvent.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class GetAllEventsbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("d")
	public ArrayList<GetAllEvents> GetAllEventsList = new ArrayList<GetAllEvents>();

	public class GetAllEvents implements Serializable {
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

		public String getHEADER_CONTENT() {
			return HEADER_CONTENT;
		}

		public void setHEADER_CONTENT(String hEADER_CONTENT) {
			HEADER_CONTENT = hEADER_CONTENT;
		}

		public String getFOOTER_CONTENT() {
			return FOOTER_CONTENT;
		}

		public void setFOOTER_CONTENT(String fOOTER_CONTENT) {
			FOOTER_CONTENT = fOOTER_CONTENT;
		}

		public String getEVT_ID() {
			return EVT_ID;
		}

		public void setEVT_ID(String eVT_ID) {
			EVT_ID = eVT_ID;
		}

		public String getEVT_NAME() {
			return EVT_NAME;
		}

		public void setEVT_NAME(String eVT_NAME) {
			EVT_NAME = eVT_NAME;
		}

		public String getEVT_SHORT_NAME() {
			return EVT_SHORT_NAME;
		}

		public void setEVT_SHORT_NAME(String eVT_SHORT_NAME) {
			EVT_SHORT_NAME = eVT_SHORT_NAME;
		}

		public String getEVT_START_DATE() {
			return EVT_START_DATE;
		}

		public void setEVT_START_DATE(String eVT_START_DATE) {
			EVT_START_DATE = eVT_START_DATE;
		}

		public String getEVT_END_DATE() {
			return EVT_END_DATE;
		}

		public void setEVT_END_DATE(String eVT_END_DATE) {
			EVT_END_DATE = eVT_END_DATE;
		}

		public String getEVT_START_TIME() {
			return EVT_START_TIME;
		}

		public void setEVT_START_TIME(String eVT_START_TIME) {
			EVT_START_TIME = eVT_START_TIME;
		}

		public String getEVT_END_TIME() {
			return EVT_END_TIME;
		}

		public void setEVT_END_TIME(String eVT_END_TIME) {
			EVT_END_TIME = eVT_END_TIME;
		}

		public String getEVT_VENUE() {
			return EVT_VENUE;
		}

		public void setEVT_VENUE(String eVT_VENUE) {
			EVT_VENUE = eVT_VENUE;
		}

		public String getEVT_CITY() {
			return EVT_CITY;
		}

		public void setEVT_CITY(String eVT_CITY) {
			EVT_CITY = eVT_CITY;
		}

		public String getEVT_STATE() {
			return EVT_STATE;
		}

		public void setEVT_STATE(String eVT_STATE) {
			EVT_STATE = eVT_STATE;
		}

		public String getEVT_WEB_URL() {
			return EVT_WEB_URL;
		}

		public void setEVT_WEB_URL(String eVT_WEB_URL) {
			EVT_WEB_URL = eVT_WEB_URL;
		}

		public String getEVT_EMAIL_ADDRESS() {
			return EVT_EMAIL_ADDRESS;
		}

		public void setEVT_EMAIL_ADDRESS(String eVT_EMAIL_ADDRESS) {
			EVT_EMAIL_ADDRESS = eVT_EMAIL_ADDRESS;
		}

		public String getEVT_IS_CURRENT() {
			return EVT_IS_CURRENT;
		}

		public void setEVT_IS_CURRENT(String eVT_IS_CURRENT) {
			EVT_IS_CURRENT = eVT_IS_CURRENT;
		}

		public String getEVT_COUNTRY() {
			return EVT_COUNTRY;
		}

		public void setEVT_COUNTRY(String eVT_COUNTRY) {
			EVT_COUNTRY = eVT_COUNTRY;
		}

		public String getEVT_CONTACT_PERSON() {
			return EVT_CONTACT_PERSON;
		}

		public void setEVT_CONTACT_PERSON(String eVT_CONTACT_PERSON) {
			EVT_CONTACT_PERSON = eVT_CONTACT_PERSON;
		}

		public String getEVT_CONTACT_NUMBER() {
			return EVT_CONTACT_NUMBER;
		}

		public void setEVT_CONTACT_NUMBER(String eVT_CONTACT_NUMBER) {
			EVT_CONTACT_NUMBER = eVT_CONTACT_NUMBER;
		}

		public String getEVT_ZIP_CODE() {
			return EVT_ZIP_CODE;
		}

		public void setEVT_ZIP_CODE(String eVT_ZIP_CODE) {
			EVT_ZIP_CODE = eVT_ZIP_CODE;
		}

		public String getEVT_LOGO_PATH() {
			return EVT_LOGO_PATH;
		}

		public void setEVT_LOGO_PATH(String eVT_LOGO_PATH) {
			EVT_LOGO_PATH = eVT_LOGO_PATH;
		}

		public String getEVT_BADGE_ALLOW() {
			return EVT_BADGE_ALLOW;
		}

		public void setEVT_BADGE_ALLOW(String eVT_BADGE_ALLOW) {
			EVT_BADGE_ALLOW = eVT_BADGE_ALLOW;
		}

		public String getEVT_BADGE_FORMAT() {
			return EVT_BADGE_FORMAT;
		}

		public void setEVT_BADGE_FORMAT(String eVT_BADGE_FORMAT) {
			EVT_BADGE_FORMAT = eVT_BADGE_FORMAT;
		}

		public String getEVT_CREATED_USER_ID() {
			return EVT_CREATED_USER_ID;
		}

		public void setEVT_CREATED_USER_ID(String eVT_CREATED_USER_ID) {
			EVT_CREATED_USER_ID = eVT_CREATED_USER_ID;
		}

		public String getEVT_CREATED_DATE() {
			return EVT_CREATED_DATE;
		}

		public void setEVT_CREATED_DATE(String eVT_CREATED_DATE) {
			EVT_CREATED_DATE = eVT_CREATED_DATE;
		}

		public String getEVT_LAST_CHANGED_USER_ID() {
			return EVT_LAST_CHANGED_USER_ID;
		}

		public void setEVT_LAST_CHANGED_USER_ID(String eVT_LAST_CHANGED_USER_ID) {
			EVT_LAST_CHANGED_USER_ID = eVT_LAST_CHANGED_USER_ID;
		}

		public String getEVT_LAST_CHANGED_DATE() {
			return EVT_LAST_CHANGED_DATE;
		}

		public void setEVT_LAST_CHANGED_DATE(String eVT_LAST_CHANGED_DATE) {
			EVT_LAST_CHANGED_DATE = eVT_LAST_CHANGED_DATE;
		}

		public String getEVT_RECORD_STATUS_FLAG() {
			return EVT_RECORD_STATUS_FLAG;
		}

		public void setEVT_RECORD_STATUS_FLAG(String eVT_RECORD_STATUS_FLAG) {
			EVT_RECORD_STATUS_FLAG = eVT_RECORD_STATUS_FLAG;
		}

		public String getEVT_RECORD_STATUS_DATE() {
			return EVT_RECORD_STATUS_DATE;
		}

		public void setEVT_RECORD_STATUS_DATE(String eVT_RECORD_STATUS_DATE) {
			EVT_RECORD_STATUS_DATE = eVT_RECORD_STATUS_DATE;
		}

		public String getEVT_TRANSACTION_GUID() {
			return EVT_TRANSACTION_GUID;
		}

		public void setEVT_TRANSACTION_GUID(String eVT_TRANSACTION_GUID) {
			EVT_TRANSACTION_GUID = eVT_TRANSACTION_GUID;
		}

		public String getEVT_RECORD_TIMESTAMP() {
			return EVT_RECORD_TIMESTAMP;
		}

		public void setEVT_RECORD_TIMESTAMP(String eVT_RECORD_TIMESTAMP) {
			EVT_RECORD_TIMESTAMP = eVT_RECORD_TIMESTAMP;
		}

		public String getEVT_QC_FLAG_DONE() {
			return EVT_QC_FLAG_DONE;
		}

		public void setEVT_QC_FLAG_DONE(String eVT_QC_FLAG_DONE) {
			EVT_QC_FLAG_DONE = eVT_QC_FLAG_DONE;
		}

		public String getREG_ID() {
			return REG_ID;
		}

		public void setREG_ID(String rEG_ID) {
			REG_ID = rEG_ID;
		}

		public String getBADGE_NO() {
			return BADGE_NO;
		}

		public void setBADGE_NO(String bADGE_NO) {
			BADGE_NO = bADGE_NO;
		}

		public String getFlag() {
			return Flag;
		}

		public void setFlag(String flag) {
			Flag = flag;
		}

		public String getMesg() {
			return Mesg;
		}

		public void setMesg(String mesg) {
			Mesg = mesg;
		}

		public String URL;

		public String getURL() {
			return URL;
		}

		public void setURL(String uRL) {
			URL = uRL;
		}

	}

}
