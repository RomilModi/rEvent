package com.rays.rEvent.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class UserProfilebean implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@SerializedName("d")
	public ArrayList<GetUserProfile> GetUserProfileDetails = new ArrayList<GetUserProfile>();

	public class GetUserProfile implements Serializable {
		private static final long serialVersionUID = 1L;
		@SerializedName("MEM_ID")
		public String MEM_ID;
		@SerializedName("MEM_FIRST_NAME")
		public String MEM_FIRST_NAME;
		@SerializedName("MEM_MIDDLE_NAME")
		public String MEM_MIDDLE_NAME;
		@SerializedName("MEM_LAST_NAME")
		public String MEM_LAST_NAME;
		@SerializedName("MEM_TITLE")
		public String MEM_TITLE;
		@SerializedName("MEM_GENDER")
		public String MEM_GENDER;
		@SerializedName("MEM_DOB")
		public String MEM_DOB;
		@SerializedName("MEM_ADDRESS1")
		public String MEM_ADDRESS1;
		@SerializedName("MEM_ADDRESS2")
		public String MEM_ADDRESS2;
		@SerializedName("MEM_MOBILE")
		public String MEM_MOBILE;
		@SerializedName("MEM_ZIP_CODE")
		public String MEM_ZIP_CODE;
		@SerializedName("MEM_TOWN")
		public String MEM_TOWN;
		@SerializedName("MEM_COUNTRY")
		public String MEM_COUNTRY;
		@SerializedName("MEM_FAX")
		public String MEM_FAX;
		@SerializedName("MEM_EMAIL")
		public String MEM_EMAIL;
		@SerializedName("MEM_DESIGNATION")
		public String MEM_DESIGNATION;
		@SerializedName("MEM_PHOTO_URL")
		public String MEM_PHOTO_URL;
		@SerializedName("MEM_PASSWORD")
		public String MEM_PASSWORD;
		@SerializedName("MEM_ACCOUNT_LOCK_FLAG")
		public String MEM_ACCOUNT_LOCK_FLAG;
		@SerializedName("MEM_LAST_LOGIN_DATE")
		public String MEM_LAST_LOGIN_DATE;
		@SerializedName("MEM_UNSUCCESSFUL_LOGIN_ATTEMPT")
		public String MEM_UNSUCCESSFUL_LOGIN_ATTEMPT;
		@SerializedName("MEM_RECORD_STATUS_FLAG")
		public String MEM_RECORD_STATUS_FLAG;
		@SerializedName("Flag")
		public String Flag;
		@SerializedName("Mesg")
		public String Mesg;
	}

}
