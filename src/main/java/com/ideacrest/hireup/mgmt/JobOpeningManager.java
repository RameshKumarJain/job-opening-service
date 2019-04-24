package com.ideacrest.hireup.mgmt;

import java.util.List;

import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ideacrest.app.validator.BeanValidatorUtility;
import com.ideacrest.hireup.bean.JobOpening;
import com.ideacrest.hireup.dao.JobOpeningDao;

@Singleton
public class JobOpeningManager {

	private JobOpeningDao jobOpeningDao;

	private BeanValidatorUtility beanValidatorUtility;

	@Inject
	public JobOpeningManager(JobOpeningDao jobOpeningDao, BeanValidatorUtility beanValidatorUtility) {
		this.jobOpeningDao = jobOpeningDao;
		this.beanValidatorUtility = beanValidatorUtility;
	}

	public Response getJobOpeningsById(long id) {
		JobOpening jobOpeningBean = jobOpeningDao.getJobOpeningsById(id);
		if (jobOpeningBean == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Jobs with id : " + id + " does not exist")
					.build();
		}
		return Response.status(Response.Status.OK).entity(jobOpeningBean).build();
	}

	public Response getAllJobOpenings() {
		List<JobOpening> jobOpenings;
		jobOpenings = jobOpeningDao.getAllJobOpenings();
		return Response.status(Response.Status.OK).entity(jobOpenings).build();
	}

	public Response addJobOpenings(JobOpening jobOpeningBean) {

		List<String> validationMessage = beanValidatorUtility.validateBean(jobOpeningBean);
		if (validationMessage.size() > 0) {
			return validationErrorResponse(validationMessage);
		}
		return addJobOpeningsAndGetResponse(jobOpeningBean);

	}

	public Response updateJobOpenings(JobOpening jobOpeningBean) {

		List<String> validationMessage = beanValidatorUtility.validateBean(jobOpeningBean);
		if (validationMessage.size() > 0) {
			return validationErrorResponse(validationMessage);
		}
		return updateJobOpeningsAndGetResponse(jobOpeningBean);

	}

	private Response addJobOpeningsAndGetResponse(JobOpening jobOpeningBean) {
		JobOpening jobOpeningInDB = jobOpeningDao.getJobOpeningsById(jobOpeningBean.get_id());
		if (jobOpeningInDB == null) {
			jobOpeningDao.insertOne(jobOpeningBean);
			return Response.status(Response.Status.OK).entity(jobOpeningBean.get_id()).build();
		}
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("Jobs with id : " + jobOpeningBean.get_id() + " already exist").build();
	}

	private Response updateJobOpeningsAndGetResponse(JobOpening jobOpeningBean) {
		JobOpening jobOpeningInDB = jobOpeningDao.getJobOpeningsById(jobOpeningBean.get_id());
		if (jobOpeningInDB == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Jobs with id : " + jobOpeningBean.get_id() + " does not exist").build();
		}
		jobOpeningDao.updateOne(jobOpeningBean, jobOpeningBean.get_id());
		return Response.status(Response.Status.OK).entity(jobOpeningBean.get_id()).build();
	}

	private Response validationErrorResponse(List<String> validationMessage) {
		return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
	}

}
