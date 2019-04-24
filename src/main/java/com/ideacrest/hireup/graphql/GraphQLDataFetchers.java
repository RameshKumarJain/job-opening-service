package com.ideacrest.hireup.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ideacrest.hireup.dao.JobOpeningDao;

import graphql.schema.AsyncDataFetcher;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Singleton
public class GraphQLDataFetchers {

	private Logger LOGGER = LoggerFactory.getLogger(GraphQLDataFetchers.class);

	private JobOpeningDao jobOpeningDao;

	@Inject
	public GraphQLDataFetchers(JobOpeningDao jobOpeningDao) {
		this.jobOpeningDao = jobOpeningDao;
	}

	public DataFetcher getJobsByIdDataFetcher() {
		return AsyncDataFetcher.async(dataFetchingEnvironment -> {
			LOGGER.info(getExecutionPathFromDataFetchingEnvironment(dataFetchingEnvironment)
					+ " getJobsByIdDataFetcher --> fetcher is called - " + System.currentTimeMillis());
			String jobOpeningId = dataFetchingEnvironment.getArgument("_id");
			return jobOpeningDao.getJobOpeningsById(Long.valueOf(jobOpeningId));
		});
	}

	public DataFetcher getAllJobs() {
		return AsyncDataFetcher.async(dataFetchingEnvironment -> {
			LOGGER.info(getExecutionPathFromDataFetchingEnvironment(dataFetchingEnvironment)
					+ " getAllJobs --> fetcher is called - " + System.currentTimeMillis());
			return jobOpeningDao.getAllJobOpenings();
		});
	}

	private String getExecutionPathFromDataFetchingEnvironment(DataFetchingEnvironment dataFetchingEnvironment) {
		return dataFetchingEnvironment.getExecutionStepInfo().getPath().toString();
	}

}
