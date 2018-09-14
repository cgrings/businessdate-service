package com.sample.business.controller;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.business.service.BusinessDateService;

@ApplicationScoped
@Path("/enddate")
public class BusinessDateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessDateController.class);

	@Inject
	private BusinessDateService businessDateService;
	
	@GET
	@Path("/{start}/{duration}")
	@Produces(MediaType.APPLICATION_JSON)
	public LocalDate businessDate(@PathParam(value = "start") final LocalDate startDateInclusive, @PathParam(value = "duration") final Integer daysDuration) {
		LOGGER.info("Start date: '{}', days: '{}'.", startDateInclusive, daysDuration);
		return businessDateService.getNextBusinessDate(startDateInclusive, daysDuration);
	}

}
