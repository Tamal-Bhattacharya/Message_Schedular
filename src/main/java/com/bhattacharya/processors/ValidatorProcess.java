package com.bhattacharya.processors;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import com.bhattacharya.requests.PostFormURLEncoded;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

import org.springframework.stereotype.Service;

@Service
public class ValidatorProcess {
	
	PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

	private Exception Exception;

	public boolean isMsgValidate(PostFormURLEncoded message) {
		if (message.getUserName().isEmpty()) {
			return false;
		}

		if (!message.getPassword().isEmpty()) {
			if (!Pattern.matches("[a-zA-Z@._0-9]{5,32}", message.getPassword())) {
				return false;
			}
		} else {
			return false;
		}

		if (message.getMsg().isEmpty()) {
			return false;
		}

		if (!message.getTime().isEmpty()) {
			if (!isTimeValid(message.getTime())) {
				return false;
			}
		} else {
			return false;
		}

		if (!phoneNumberUtil.isPossibleNumber(message.getSendTo(),"IN")) {
			return false;
		}

		return true;
	}

	private boolean isTimeValid(String time) {
		try {
			LocalDateTime scheduledTime = LocalDateTime.parse(time.replace(" ", "T"));
			if (scheduledTime.isBefore(LocalDateTime.now())) {
				throw Exception;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
