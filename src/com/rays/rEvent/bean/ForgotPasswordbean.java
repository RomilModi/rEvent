package com.rays.rEvent.bean;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class ForgotPasswordbean {

	@Expose
	public List<D> d = new ArrayList<D>();

	/**
	 * 
	 * @return The d
	 */
	public List<D> getD() {
		return d;
	}

	/**
	 * 
	 * @param d
	 *            The d
	 */
	public void setD(List<D> d) {
		this.d = d;
	}

	public class D {

		@Expose
		private String Flag;
		@Expose
		private String Mesg;

		/**
		 * 
		 * @return The Flag
		 */
		public String getFlag() {
			return Flag;
		}

		/**
		 * 
		 * @param Flag
		 *            The Flag
		 */
		public void setFlag(String Flag) {
			this.Flag = Flag;
		}

		/**
		 * 
		 * @return The Mesg
		 */
		public String getMesg() {
			return Mesg;
		}

		/**
		 * 
		 * @param Mesg
		 *            The Mesg
		 */
		public void setMesg(String Mesg) {
			this.Mesg = Mesg;
		}
	}
}
