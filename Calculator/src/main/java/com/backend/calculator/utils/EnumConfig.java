package com.backend.calculator.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/*
 * 
 * This class contains the configurations for enum format conversion.
 *   
 * 	@author  Kannan Maniyan
 * 	@version 1.0
 * 	@since   26-01-2020 
*/
@Configuration
public class EnumConfig extends WebMvcConfigurationSupport {
	@Override
	public FormattingConversionService mvcConversionService() {
		FormattingConversionService formattingConversionService = super.mvcConversionService();
		formattingConversionService.addConverter(new SimpleEnumConverter());
		formattingConversionService.addConverter(new SciEnumConverter());
		return formattingConversionService;
	}

	public class SimpleEnumConverter implements Converter<String, SimpleOperationEnum> {
		@Override
		public SimpleOperationEnum convert(String source) {
			try {
				return SimpleOperationEnum.valueOf(source.toUpperCase());
			} catch (Exception e) {
				return null;
			}
		}
	}

	public class SciEnumConverter implements Converter<String, SciOperationEnum> {
		@Override
		public SciOperationEnum convert(String source) {
			try {
				return SciOperationEnum.valueOf(source.toUpperCase());
			} catch (Exception e) {
				return null;
			}
		}
	}
}
