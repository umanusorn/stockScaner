package um.vi8e.com.stocktakescanner.utils;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Created by Fixer on 11/26/2015.
 */
public class networkUtil {
public static String GET(String url){
	InputStream inputStream = null;
	String result = "";
	try {

		// create HttpClient
		HttpClient httpclient = new DefaultHttpClient();

		// make GET request to the given URL
		HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

		// receive response as inputStream
		inputStream = httpResponse.getEntity().getContent();

		// convert inputstream to string
		if(inputStream != null)
			result = convertInputStreamToString(inputStream);
		else
			result = "Did not work!";

	} catch (Exception e) {
		Log.d("InputStream", e.getLocalizedMessage());
	}

	return result;
}

private static String convertInputStreamToString(InputStream inputStream) throws IOException {
	BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	String line = "";
	String result = "";
	while((line = bufferedReader.readLine()) != null)
		result += line;

	inputStream.close();
	return result;

}

public static HashMap<String, String> jsonToMap(String t) throws JSONException {

	HashMap<String, String> map = new HashMap<String, String>();
	JSONObject jObject = new JSONObject(t);
	Iterator<?> keys = jObject.keys();

	while( keys.hasNext() ){
		String key = (String)keys.next();
		String value = jObject.getString(key);
		map.put(key, value);

	}
/*
	System.out.println("json : " + jObject);
	System.out.println("map : " + map);*/
	return map;
}
}
