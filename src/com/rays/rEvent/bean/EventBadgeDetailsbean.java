package com.rays.rEvent.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;
import com.rays.rEvent.bean.EventBadgebean.GetBadgeDetails;

public class EventBadgeDetailsbean implements Serializable {

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
	}

}
