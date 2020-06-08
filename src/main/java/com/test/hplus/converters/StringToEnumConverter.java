package com.test.hplus.converters;

import org.springframework.core.convert.converter.Converter;

import com.test.beans.Gender;

public class StringToEnumConverter implements Converter<String, Gender>{

	@Override
	public Gender convert(String source) {
		// TODO Auto-generated method stub
		if(source.equalsIgnoreCase("MALE")) {
			return Gender.MALE;
		}else if(source.equalsIgnoreCase("FEMALE")) {
			return Gender.FEMALE;
		}else {
			return Gender.OTHER;
		}  
	}

}
