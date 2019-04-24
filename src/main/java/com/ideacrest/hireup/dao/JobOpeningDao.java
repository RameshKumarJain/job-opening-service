package com.ideacrest.hireup.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.ideacrest.app.mongo.MongoService;
import com.ideacrest.hireup.bean.JobOpening;
import com.mongodb.client.MongoDatabase;

public class JobOpeningDao extends MongoService<JobOpening> {

	public static final String JOB_OPENING_COLLECTION_NAME = "job_openings";

	public static final String ID = "_id";

	@Inject
	public JobOpeningDao(MongoDatabase db, ObjectMapper objectMapper) {
		super(db, objectMapper, JOB_OPENING_COLLECTION_NAME, JobOpening.class);
	}

	public JobOpening getJobOpeningsById(long id) {
		return findOneByKey(eq(ID, id));
	}

	public List<JobOpening> getAllJobOpenings() {
		return find();
	}

	public void addJobOpenings(JobOpening jobOpeningBean) {
		insertOne(jobOpeningBean);
	}

	public void updateJobOpenings(JobOpening jobOpeningBean) {
		updateOne(jobOpeningBean, jobOpeningBean.get_id());
	}
}
