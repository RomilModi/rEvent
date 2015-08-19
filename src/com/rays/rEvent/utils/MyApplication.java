package com.rays.rEvent.utils;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		// UNIVERSAL IMAGE LOADER SETUP
		// DisplayImageOptions defaultOptions = new
		// DisplayImageOptions.Builder()
		// .cacheOnDisc(true).cacheInMemory(true)
		// .imageScaleType(ImageScaleType.EXACTLY)
		// .displayer(new FadeInBitmapDisplayer(300)).build();
		//
		// ImageLoaderConfiguration config = new
		// ImageLoaderConfiguration.Builder(
		// getApplicationContext())
		// .defaultDisplayImageOptions(defaultOptions)
		// .memoryCache(new WeakMemoryCache())
		// .discCacheSize(100 * 1024 * 1024).build();
		//
		// ImageLoader.getInstance().init(config);

		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).threadPoolSize(3)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory().memoryCacheSize(4194304)
				.defaultDisplayImageOptions(defaultOptions).build();

		ImageLoader.getInstance().init(config);
		// END - UNIVERSAL IMAGE LOADER SETUP
	}
}
