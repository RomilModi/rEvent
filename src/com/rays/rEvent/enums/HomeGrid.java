package com.rays.rEvent.enums;

import com.rays.rEvent.EventBadgeActivity;
import com.rays.rEvent.EventNearByRestaurantsActivity;
import com.rays.rEvent.EventVanueActivity;
import com.rays.rEvent.R;
import com.rays.rEvent.ServicesActivity;

public enum HomeGrid {
	
	SERVICES(0, R.string.services ,ServicesActivity.class,R.drawable.services, true), 
	BADGE(1, R.string.badge, EventBadgeActivity.class, R.drawable.badge, true), 
	VANUE(2, R.string.vanue,EventVanueActivity.class, R.drawable.venue, false),
//	PROFILE(3, R.string.profile, ServicesActivity.class, R.drawable.profile, true),
	PDF(4, R.string.pdf, null, R.drawable.pdf, false),
	RESTAURANTS(5, R.string.restaurants, EventNearByRestaurantsActivity.class, R.drawable.restaurants, true),
	SUPPORT(6, R.string.support,ServicesActivity.class, R.drawable.support, false);
//	COMPLAINT_REGISTRATION(7, R.string.services, ServicesActivity.class, R.drawable.support, true), 
//	FATCS_AND_FIGURES(8, R.string.badge,ServicesActivity.class, R.drawable.badge, false);
	private final int index;
	private final int name;
	private Class klass;
	private Integer drawable;
	private Integer drawableGUJ;
	private boolean isActive;

	HomeGrid(int index, int name, Class klass, Integer drawable, boolean isActive) {
		this.index = index;
		this.name = name;
		this.klass = klass;
		this.drawable = drawable;
		this.isActive = isActive;
	}

	public static HomeGrid getByIndex(int index) {
		return (index <= HomeGrid.values().length) ? HomeGrid.values()[index] : SUPPORT;
	}

	public int getIndex() {
		return index;
	}

	public int getName() {
		return name;
	}


	public Class getKlass() {
		return klass;
	}

	public boolean isActive() {
		return isActive;
	}

	public Integer getDrawable() {
		return drawable;
	}

	public Integer getDrawableGUJ() {
		return drawableGUJ;
	}

}
