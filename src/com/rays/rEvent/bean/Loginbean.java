package com.rays.rEvent.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Loginbean implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@SerializedName("d")
	public ArrayList<GetLogin> GetLoginDetails = new ArrayList<GetLogin>();

	public class GetLogin implements Serializable {
		private static final long serialVersionUID = 1L;
		@SerializedName("FLAGE")
		public String FLAGE;
		@SerializedName("COM_ID")
		public String COM_ID;
		@SerializedName("MEMBER_ID")
		public String MEMBER_ID;

	}
}
