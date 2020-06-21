package com.drmodi.locationweb.utils;

import org.springframework.stereotype.Component;

@Component
public interface EmailUtil {
	
	public void sendEmail(String toAddress, String subject, String body);

}
