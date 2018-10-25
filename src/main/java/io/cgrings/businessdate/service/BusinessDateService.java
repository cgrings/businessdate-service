package io.cgrings.businessdate.service;

import java.time.LocalDate;

public interface BusinessDateService {

	LocalDate getNextBusinessDate(final LocalDate startDateInclusive, final Integer daysDuration);
}
