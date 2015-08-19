package com.rays.rEvent.RequestTask;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.util.Log;

public class RestFulWebservice {

	public String executeWS(String serverURL, String contentType, String data) {
		String returnVal = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpPost httpPost = new HttpPost(serverURL);
		try {
			httpPost.setHeader("Content-Type", contentType);
			StringEntity sEntity = new StringEntity(data);
			httpPost.setEntity(sEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost,
					localContext);
			HttpEntity entity = httpResponse.getEntity();
			returnVal = getASCIIContentFromEntity(entity);
			int maxLogSize = 1000;
			int start = 0, end = 0;
			for (int i = 0; i <= returnVal.length() / maxLogSize; i++) {
				start = i * maxLogSize;
				end = (i + 1) * maxLogSize;
				end = end > returnVal.length() ? returnVal.length() : end;
				Log.e("TAG", "Response : " + returnVal.substring(start, end));
			}
			// returnVal = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return returnVal;
	}

	private String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);
			if (n > 0)
				out.append(new String(b, 0, n));
		}
		return out.toString();
	}
}
