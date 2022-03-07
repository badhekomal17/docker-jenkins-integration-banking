package com.zensar.banking.constant;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Validation {

	// Validation of new registration
	public boolean validateData(String input, String data) {
		String patternString = " ";
		boolean matches = false;

		switch (input) {
		// Validation for customer id
		case "cid":
			patternString = "^[0-9]$";
			matches = Pattern.matches(patternString, data);
			break;

		// Validation for names(first_name,last_name,all_address)
		case "names":
			patternString = "^(.+)[a-zA-Z](.+)$";
			matches = Pattern.matches(patternString, data);
			break;

		// Validation for email
		case "email":
			patternString = "^(.+)@(.+)$";
			matches = Pattern.matches(patternString, data);
			break;

		// Validation for mobileNo
		case "mobileNo":
			patternString = "^\\d{10}$";
			matches = Pattern.matches(patternString, data);
			break;

		// Validation for adharNo
		case "adharNo":
			patternString = "^\\d{12}$";
			matches = Pattern.matches(patternString, data);
			break;

		// Validation for panNo
		case "panNo":
			patternString = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
			matches = Pattern.matches(patternString, data);
			break;

		// Validation for pinCode
		case "pinCode":
			// Regex to check valid pin code of India.
			String regex = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$";
			matches = Pattern.matches(regex, data);
			break;

		// Validation for Password
		case "password":
			patternString = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
			matches = Pattern.matches(patternString, data);
			break;
		}
		return matches;
	}// end validateData()
}
