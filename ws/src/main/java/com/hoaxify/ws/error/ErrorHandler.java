package com.hoaxify.ws.error;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


@RestController

public class ErrorHandler implements ErrorController{

	@Autowired
	private ErrorAttributes errorAttributes;
	
	@RequestMapping("${server.error.path:${error.path:/error}}")
	public ApiError error(WebRequest webRequest) {
		 ErrorAttributeOptions errorAttributeOptions = ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE, Include.BINDING_ERRORS);
		Map<String, Object> attributes = this.errorAttributes.getErrorAttributes(webRequest, errorAttributeOptions);
		String message = (String)attributes.get("message");
		String path = (String)attributes.get("path");
		int status = (Integer)attributes.get("status");
		
		ApiError error = new ApiError(status,message,path);
		if(attributes.containsKey("errors")) {
			@SuppressWarnings("unchecked")
			List<FieldError> fieldErrors = (List<FieldError>)attributes.get("errors");
			Map<String, String> validationErrors = new HashMap<>();
			for(FieldError fieldError : fieldErrors) {
				validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}	
			error.setValidationErrors(validationErrors);
		}
		
		
		return error;
	}
     
	public String getErrorPath() {
		throw new RuntimeException("This will not be called.");
     }
	
	
}
