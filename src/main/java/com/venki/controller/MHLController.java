package com.venki.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.venki.pojo.RequestPOJO;

@RestController
@RequestMapping("/")
public class MHLController {
	private final String sharedKey = "SHARED_KEY";
	
	private static final String ERROR_STATUS = "error";
	private static final String CODE_SUCCESS = "SUCCESS";
	private static final String CODE_FAILURE = "FAILURE";

	@RequestMapping(value = "/servicekey", method = RequestMethod.POST)
	public RequestPOJO serviceKey(@RequestParam(value = "key") String key, @RequestBody RequestPOJO request) {
		//RequestPOJO response = new RequestPOJO();
		if (sharedKey.equalsIgnoreCase(key)) {
			String userId = request.getUserId();
			// Process the request
			// ....
			// Return success response to the client.
			request.setRemark("");
			request.setCode(CODE_SUCCESS);
		} else {
			/*request.setStatus(ERROR_STATUS);
			request.setCode(AUTH_FAILURE);*/
		}
		return request;
	}
	
	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public RequestPOJO validate(@RequestBody RequestPOJO request) {
		
		if (request.getUserId()!=null && request.getUserId().equalsIgnoreCase("venkat")) {
			request.setRemark("User successfully validated");
			request.setCode(CODE_SUCCESS);
		} else {
			request.setRemark("Failed to validate user");
			request.setCode(CODE_FAILURE);
		}
		return request;
	}
	
	
	@RequestMapping(value = "/validateKey", method = RequestMethod.POST)
	public static RequestPOJO validateKey(@RequestBody RequestPOJO request){
		
		if (request.getKeyId()!=null && request.getKeyId().equalsIgnoreCase("123")) {
			request.setRemark("KEY successfully validated");
			request.setCode(CODE_SUCCESS);
		} else {
			request.setRemark("Failed to validate KEY");
			request.setCode(CODE_FAILURE);
		}
		return request;
		
	}
}