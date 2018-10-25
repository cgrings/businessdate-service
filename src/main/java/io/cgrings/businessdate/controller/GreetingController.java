package io.cgrings.businessdate.controller;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/greeting")
public class GreetingController {

	@GET
	public String hello(@DefaultValue("World") @QueryParam("name") final String name) {
		return String.format("Hello %s!", name);
	}
}
