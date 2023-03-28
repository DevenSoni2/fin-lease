package com.nagarro.fin.lease.app.config;

import org.hibernate.id.enhanced.SequenceStyleGenerator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.springframework.data.mapping.MappingException;
import org.hibernate.type.Type;

public class DatePrefixedSequenceIdGenerator extends SequenceStyleGenerator {

	public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
	public static final String VALUE_PREFIX_DEFAULT = "";
	private String valuePrefix;
	public static final String DATE_FORMAT_PARAMETER = "dateFormat";
	public static final String DATE_FORMAT_DEFAULT = "%tY%tm%td";

	public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
	public static final String NUMBER_FORMAT_DEFAULT = "%05d";
	private String numberFormat;

	public static final String DATE_NUMBER_SEPARATOR_PARAMETER = "dateNumberSeparator";
	public static final String DATE_NUMBER_SEPARATOR_DEFAULT = "";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		LocalDate date = LocalDate.now();
		return valuePrefix + String.format(DATE_FORMAT_DEFAULT, date, date, date)
				+ String.format(numberFormat, super.generate(session, object));
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		super.configure(LongType.INSTANCE, params, serviceRegistry);
		valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
		numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);

	}
}