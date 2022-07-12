/**
 * 
 */
package com.ticketbookingplatform.util;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalTime attribute) {
		return DateUtils.convertLocalTimeToDate(attribute);
	}

	@Override
	public LocalTime convertToEntityAttribute(Date dbData) {
		return DateUtils.getLocalTimeFromDate(dbData);
	}

}