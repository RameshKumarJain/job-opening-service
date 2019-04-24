package com.ideacrest.hireup.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.ideacrest.hireup.bean.JobOpening;
import com.ideacrest.hireup.mgmt.JobOpeningManager;

@Path("/job-openings")
@Produces(MediaType.APPLICATION_JSON)
public class JobOpeningsResource {

	private JobOpeningManager jobOpeningManager;

	@Inject
	public JobOpeningsResource(JobOpeningManager jobOpeningManager) {
		this.jobOpeningManager = jobOpeningManager;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addJobOpenings(JobOpening jobOpeningBean) {
		return jobOpeningManager.addJobOpenings(jobOpeningBean);

	}

	@GET
	public Response getJobOpeningsById(@QueryParam("id") long id) {
		return jobOpeningManager.getJobOpeningsById(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateJobOpenings(JobOpening jobOpeningBean) {
		return jobOpeningManager.updateJobOpenings(jobOpeningBean);
	}

	@GET
	@Path("all")
	public Response getAllJobOpenings() {
		return jobOpeningManager.getAllJobOpenings();
	}
}
