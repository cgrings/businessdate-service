package com.sample.business.service;

import java.time.LocalDate;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jollyday.Holiday;
import de.jollyday.HolidayManager;
import de.jollyday.parameter.CalendarPartManagerParameter;
import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.jdk8.LocalDateKitCalculatorsFactory;

@ApplicationScoped
public class BusinessDateServiceImpl implements BusinessDateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessDateService.class); 

	@Override
	public LocalDate getNextBusinessDate(LocalDate startDateInclusive, Integer daysDuration) {
		final LocalDate endDateInclusive = startDateInclusive.plusDays(daysDuration * 2);

		final CalendarPartManagerParameter managerParameter = new CalendarPartManagerParameter("br", new Properties());
		final HolidayManager holidayManager = HolidayManager.getInstance(managerParameter);
		final Set<Holiday> holidays = holidayManager.getHolidays(startDateInclusive, endDateInclusive, "rs");

		final Set<LocalDate> holidayDates = holidays.stream().map(h -> h.getDate()).collect(Collectors.toSet());
		final DefaultHolidayCalendar<LocalDate> holidaysCalendar = new DefaultHolidayCalendar<>(holidayDates, startDateInclusive, endDateInclusive);
		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("BR", holidaysCalendar);
		final DateCalculator<LocalDate> dateCalculator = LocalDateKitCalculatorsFactory.getDefaultInstance()
				.getDateCalculator("BR", HolidayHandlerType.FORWARD)
				.setStartDate(startDateInclusive);
		final LocalDate result = dateCalculator.moveByBusinessDays(daysDuration).getCurrentBusinessDate();
		LOGGER.info("Business date: '{}'.", result);
		return result;
	}

}
