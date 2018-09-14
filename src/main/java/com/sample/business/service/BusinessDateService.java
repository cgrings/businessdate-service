package com.sample.business.service;

import java.time.LocalDate;

public interface BusinessDateService {

	LocalDate getNextBusinessDate(final LocalDate startDateInclusive, final Integer daysDuration);
}
