package io.cgrings.businessdate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import de.jollyday.Holiday;
import de.jollyday.HolidayManager;
import de.jollyday.parameter.CalendarPartManagerParameter;
import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.jdk8.LocalDateKitCalculatorsFactory;

public class WorkdayTest {

	@Test
	public void septemberHolydaysTest() {
		final CalendarPartManagerParameter managerParameter = new CalendarPartManagerParameter("br", new Properties());
		final HolidayManager holidayManager = HolidayManager.getInstance(managerParameter);
		final LocalDate startDateInclusive = LocalDate.of(2018, 9, 1);
		final LocalDate endDateInclusive = LocalDate.of(2018, 9, 30);
		final Set<Holiday> holidays = holidayManager.getHolidays(startDateInclusive, endDateInclusive, "rs");
		assertEquals(2, holidays.size());

	}

	@Test
	public void septemberWorkdaysTest() {
		final int days = 10;
		final LocalDate startDateInclusive = LocalDate.of(2018, 9, 6);
		final LocalDate endDateInclusive = startDateInclusive.plusDays(days * 2);

		final CalendarPartManagerParameter managerParameter = new CalendarPartManagerParameter("br", new Properties());
		final HolidayManager holidayManager = HolidayManager.getInstance(managerParameter);
		final Set<Holiday> holidays = holidayManager.getHolidays(startDateInclusive, endDateInclusive, "rs");

		final Set<LocalDate> holidayDates = holidays.stream().map(h -> h.getDate()).collect(Collectors.toSet());
		final DefaultHolidayCalendar<LocalDate> holidaysCalendar = new DefaultHolidayCalendar<>(holidayDates, startDateInclusive, endDateInclusive);
		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("BR", holidaysCalendar);
		final DateCalculator<LocalDate> dateCalculator = LocalDateKitCalculatorsFactory.getDefaultInstance()
				.getDateCalculator("BR", HolidayHandlerType.FORWARD)
				.setStartDate(startDateInclusive);
		final LocalDate currentBusinessDate = dateCalculator.moveByBusinessDays(days).getCurrentBusinessDate();

		assertEquals(2, holidays.size());
		assertEquals(LocalDate.of(2018, 9, 24), currentBusinessDate);
	}

}
