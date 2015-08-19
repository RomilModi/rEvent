package com.rays.rEvent.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;
import com.rays.rEvent.bean.Loginbean.GetLogin;

public class EventBadgebean implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@SerializedName("d")
	public ArrayList<GetBadgeDetails> GetGetBadgeDetails = new ArrayList<GetBadgeDetails>();

	public class GetBadgeDetails implements Serializable {
		private static final long serialVersionUID = 1L;

		@SerializedName("REG_TITLE")
		public String REG_TITLE;
		@SerializedName("REG_FIRST_NAME")
		public String REG_FIRST_NAME;
		@SerializedName("REG_MIDDLE_NAME")
		public String REG_MIDDLE_NAME;
		@SerializedName("REG_LAST_NAME")
		public String REG_LAST_NAME;
		@SerializedName("BADGE_FORMAT")
		public String BADGE_FORMAT;
		@SerializedName("REG_BADGE_NO")
		public String REG_BADGE_NO;
		@SerializedName("Flag")
		public String Flag;
		@SerializedName("Mesg")
		public String Mesg;

		public String getREG_TITLE() {
			return REG_TITLE;
		}

		public void setREG_TITLE(String rEG_TITLE) {
			REG_TITLE = rEG_TITLE;
		}

		public String getREG_FIRST_NAME() {
			return REG_FIRST_NAME;
		}

		public void setREG_FIRST_NAME(String rEG_FIRST_NAME) {
			REG_FIRST_NAME = rEG_FIRST_NAME;
		}

		public String getREG_MIDDLE_NAME() {
			return REG_MIDDLE_NAME;
		}

		public void setREG_MIDDLE_NAME(String rEG_MIDDLE_NAME) {
			REG_MIDDLE_NAME = rEG_MIDDLE_NAME;
		}

		public String getREG_LAST_NAME() {
			return REG_LAST_NAME;
		}

		public void setREG_LAST_NAME(String rEG_LAST_NAME) {
			REG_LAST_NAME = rEG_LAST_NAME;
		}

		public String getBADGE_FORMAT() {
			return BADGE_FORMAT;
		}

		public void setBADGE_FORMAT(String bADGE_FORMAT) {
			BADGE_FORMAT = bADGE_FORMAT;
		}

		public String getREG_BADGE_NO() {
			return REG_BADGE_NO;
		}

		public void setREG_BADGE_NO(String rEG_BADGE_NO) {
			REG_BADGE_NO = rEG_BADGE_NO;
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

		// @SerializedName("HEADER_CONTENT")
		// public String HEADER_CONTENT;
		// @SerializedName("FOOTER_CONTENT")
		// public String FOOTER_CONTENT;
		// @SerializedName("ASSOCIATE_TABLE")
		// public String ASSOCIATE_TABLE;
		// @SerializedName("BADGE_PATH")
		// public String BADGE_PATH;
		// @SerializedName("REG_ID")
		// public String REG_ID;
		// @SerializedName("EVT_ID")
		// public String EVT_ID;
		// @SerializedName("EVT_NAME")
		// public String EVT_NAME;
		// @SerializedName("EVT_START_DATE")
		// public String EVT_START_DATE;
		// @SerializedName("EVT_END_DATE")
		// public String EVT_END_DATE;
		// @SerializedName("REG_PARENT_ID")
		// public String REG_PARENT_ID;
		// @SerializedName("REG_TITLE")
		// public String REG_TITLE;
		// @SerializedName("REG_FIRST_NAME")
		// public String REG_FIRST_NAME;
		// @SerializedName("REG_MIDDLE_NAME")
		// public String REG_MIDDLE_NAME;
		// @SerializedName("REG_LAST_NAME")
		// public String REG_LAST_NAME;
		// @SerializedName("REG_ADDRESS")
		// public String REG_ADDRESS;
		// @SerializedName("REG_CITY")
		// public String REG_CITY;
		// @SerializedName("REG_STATE")
		// public String REG_STATE;
		// @SerializedName("REG_ZIP_CODE")
		// public String REG_ZIP_CODE;
		// @SerializedName("REG_COUNTRY")
		// public String REG_COUNTRY;
		// @SerializedName("REG_EMAIL")
		// public String REG_EMAIL;
		// @SerializedName("REG_MOBILE")
		// public String REG_MOBILE;
		// @SerializedName("REG_MOBILE2")
		// public String REG_MOBILE2;
		// @SerializedName("REG_PHONE_R")
		// public String REG_PHONE_R;
		// @SerializedName("REG_FAX_R")
		// public String REG_FAX_R;
		// @SerializedName("REG_MEMBER_PHOTO")
		// public String REG_MEMBER_PHOTO;
		// @SerializedName("REG_AGE")
		// public String REG_AGE;
		// @SerializedName("REG_BIRTHDATE")
		// public String REG_BIRTHDATE;
		// @SerializedName("REG_BLOOD_GROUP")
		// public String REG_BLOOD_GROUP;
		// @SerializedName("REG_ASSO_RELATIONSHIP")
		// public String REG_ASSO_RELATIONSHIP;
		// @SerializedName("REG_GENDER")
		// public String REG_GENDER;
		// @SerializedName("REG_BADGE_NO")
		// public String REG_BADGE_NO;
		// @SerializedName("REG_REGISTRATION_DATE")
		// public String REG_REGISTRATION_DATE;
		// @SerializedName("REG_COMPANY_NAME")
		// public String REG_COMPANY_NAME;
		// @SerializedName("REG_DESIGNATION")
		// public String REG_DESIGNATION;
		// @SerializedName("REG_COMPANY_URL")
		// public String REG_COMPANY_URL;
		// @SerializedName("REG_COMPANY_ADDRESS")
		// public String REG_COMPANY_ADDRESS;
		// @SerializedName("REG_COMPANY_CITY")
		// public String REG_COMPANY_CITY;
		// @SerializedName("REG_COMPANY_STATE")
		// public String REG_COMPANY_STATE;
		// @SerializedName("REG_COMPANY_ZIP_CODE")
		// public String REG_COMPANY_ZIP_CODE;
		// @SerializedName("REG_COMPANY_COUNTRY")
		// public String REG_COMPANY_COUNTRY;
		// @SerializedName("REG_COMPANY_PHONE")
		// public String REG_COMPANY_PHONE;
		// @SerializedName("REG_COMPANY_FAX")
		// public String REG_COMPANY_FAX;
		// @SerializedName("REG_ASSOCIATE_INCLUDE")
		// public String REG_ASSOCIATE_INCLUDE;
		// @SerializedName("REG_MEMBER_FEES")
		// public String REG_MEMBER_FEES;
		// @SerializedName("REG_SERVICE_FEES")
		// public String REG_SERVICE_FEES;
		// @SerializedName("REG_PER_ASSOCIATE_FEES")
		// public String REG_PER_ASSOCIATE_FEES;
		// @SerializedName("REG_ACCOMMODATION_FEES")
		// public String REG_ACCOMMODATION_FEES;
		// @SerializedName("REG_EARLY_DISCOUNT")
		// public String REG_EARLY_DISCOUNT;
		// @SerializedName("REG_ADDITIONAL_DISCOUNT")
		// public String REG_ADDITIONAL_DISCOUNT;
		// @SerializedName("REG_TOTAL_AMOUNT")
		// public String REG_TOTAL_AMOUNT;
		// @SerializedName("REG_PAYMENT_STATUS")
		// public String REG_PAYMENT_STATUS;
		// @SerializedName("REG_CREATED_USER_ID")
		// public String REG_CREATED_USER_ID;
		// @SerializedName("REG_CREATED_DATE")
		// public String REG_CREATED_DATE;
		// @SerializedName("REG_LAST_CHANGED_USER_ID")
		// public String REG_LAST_CHANGED_USER_ID;
		// @SerializedName("REG_LAST_CHANGED_DATE")
		// public String REG_LAST_CHANGED_DATE;
		// @SerializedName("REG_PAYMENT_CURRENCY")
		// public String REG_PAYMENT_CURRENCY;
		// @SerializedName("REG_MEMBERSHIP_NUMBER")
		// public String REG_MEMBERSHIP_NUMBER;
		// @SerializedName("REG_FEES_OVERRIDE")
		// public String REG_FEES_OVERRIDE;
		// @SerializedName("REG_BADGE_PRINT_COUNT")
		// public String REG_BADGE_PRINT_COUNT;
		// @SerializedName("REG_BADGE_PRINT_DATE")
		// public String REG_BADGE_PRINT_DATE;
		// @SerializedName("REG_RECORD_STATUS_FLAG")
		// public String REG_RECORD_STATUS_FLAG;
		// @SerializedName("REG_RECORD_STATUS_DATE")
		// public String REG_RECORD_STATUS_DATE;
		// @SerializedName("REG_TRANSACTION_GUID")
		// public String REG_TRANSACTION_GUID;
		// @SerializedName("REG_RECORD_TIMESTAMP")
		// public String REG_RECORD_TIMESTAMP;
		// @SerializedName("REG_QC_FLAG_DONE")
		// public String REG_QC_FLAG_DONE;
		// @SerializedName("Flag")
		// public String Flag;
		// @SerializedName("Mesg")
		// public String Mesg;
	}

}
