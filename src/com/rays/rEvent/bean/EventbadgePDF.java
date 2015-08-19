package com.rays.rEvent.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class EventbadgePDF {
	private static final long serialVersionUID = 1L;
	@SerializedName("d")
	public ArrayList<GetBadgePDF> GetBadgePDF = new ArrayList<GetBadgePDF>();

	public class GetBadgePDF implements Serializable {
		private static final long serialVersionUID = 1L;

		@SerializedName("PDF")
		public byte[] PDF;
	}
}
