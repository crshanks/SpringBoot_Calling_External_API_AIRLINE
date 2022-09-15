package net.springboot.springbootcallingexternalapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestSpringBootController {
	
	
	 @GetMapping("/airlines")
	    public ResponseEntity<?> getCountrys() {
	        try {
	            String uri="https://api.instantwebtools.net/v1/airlines";
	            RestTemplate resttemplate = new RestTemplate();
	            String result = resttemplate.getForObject(uri, String.class);
	            return new ResponseEntity<>(result, HttpStatus.OK);
	        }catch (Exception e){
	            e.printStackTrace();
	            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	

}
