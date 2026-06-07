// provided by the instructor
// this class contains utility methods for working with Servlets, such as reading request data and sending response
// data in JSON format.
package org.hua.it2024041;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class ServletUtil {

	/** Ensure that we will not create an instance of this class */
	private ServletUtil() {
		
	}
	
	/*
	* Get HTTP Request Body as (JSON) String and Convert it to Object of Type T
	* 
	*/
	public static <T> T getRequestData(Class<T> cls, HttpServletRequest request) throws IOException {
		// Get HTTP Request Body as (JSON) String
		final StringBuilder sb = new StringBuilder();
	    try(BufferedReader reader = request.getReader()){
	        String line;
	        while ((line = reader.readLine()) != null){
	        	sb.append(line);
	        }
	    }
	    final String payload = sb.toString();
	    // Convert (JSON) String to Object
        return jsonStrToObj(cls, payload);
	}
	
	// Convert Object to JSON String and Send it in the HTTP Response Body
	public static void sendResponseData(Object obj, HttpServletResponse response) throws IOException {
		// Set HTTP Response Content-Type
		response.setContentType("text/json");
		// Write JSON (String) in the HTTP Response Body
        PrintWriter out = response.getWriter();
        out.println(objToJsonStr(obj));
        out.flush();
        out.close();
	}
	// Get the first parameter from the URL path (e.g., /topics/123 -> returns "123")
	public static String getPathFirstParam(HttpServletRequest request) {
		final String path = request.getPathInfo();
		if (path == null) return null;
		String[] tokens = path.split("/");
		for(String token : tokens) {
			if (token.trim().isEmpty()) continue;
			return token;
		}
		return null;
	}
	
	/* The following two methods use Gson Library */
	
	private static <T> T jsonStrToObj(Class<T> cls, String str) {
		return new Gson().fromJson(str, cls);
	}
	
	private static String objToJsonStr(Object obj) {
		return new Gson().toJson(obj);
	}
	
	/** For testing purposes ... */
	
	// A Plain Old Java Object (POJO)
	// https://www.codecademy.com/resources/docs/java/pojo
	
	public static final class TestData {
		
		private final int intValue;
		public final String strValue;
		public boolean booleanValue;
		
		public TestData(int intValue, String strValue, boolean booleanValue) {
			super();
			this.intValue = intValue;
			this.strValue = strValue;
			this.booleanValue = booleanValue;
		}

		@Override
		public String toString() {
			return "TestData [intValue=" + intValue + ", strValue=" + strValue + ", booleanValue=" + booleanValue + "]";
		}
		
	}

	
	
}
