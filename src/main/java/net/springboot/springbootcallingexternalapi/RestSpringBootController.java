package net.springboot.springbootcallingexternalapi;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestSpringBootController {
	
	
	 @GetMapping("/airlines")
	    public ResponseEntity<?> getAirlines() {
	        try {
	            String uri="https://api.instantwebtools.net/v1/airlines";
				String responseBody = makeRequest(uri);
	            return new ResponseEntity<>(responseBody, HttpStatus.OK);
	        }catch (Exception e){
	            e.printStackTrace();
	            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

		public String makeRequest(String uri) throws Exception {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			try {
				HttpGet httpget = new HttpGet(uri);
	
				System.out.println("Executing request " + httpget.getRequestLine());
	
				// Create a custom response handler
				ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
	
					@Override
					public String handleResponse(
							final HttpResponse response) throws ClientProtocolException, IOException {
						int status = response.getStatusLine().getStatusCode();
						if (status >= 200 && status < 300) {
							HttpEntity entity = response.getEntity();
							return entity != null ? EntityUtils.toString(entity) : null;
						} else {
							throw new ClientProtocolException("Unexpected response status: " + status);
						}
					}
	
				};
				String responseBody = httpclient.execute(httpget, responseHandler);
				System.out.println("----------------------------------------");
				System.out.println(responseBody);
				return responseBody;
			} finally {
				httpclient.close();
			}				
		}
	

}
