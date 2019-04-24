package com.ideacrest.hireup.graphql;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ideacrest.hireup.bean.JobOpening;
import com.ideacrest.hireup.dao.JobOpeningDao;

import graphql.Scalars;
import graphql.execution.ExecutionPath;
import graphql.execution.ExecutionStepInfo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class GraphQLDataFetchersTest {

	@InjectMocks
	GraphQLDataFetchers graphQLDataFetchers;

	@Mock
	private JobOpeningDao jobOpeningDao;

	@Mock
	private DataFetchingEnvironment environment;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(environment.getExecutionStepInfo()).thenReturn(ExecutionStepInfo.newExecutionStepInfo()
				.type(Scalars.GraphQLString).path(ExecutionPath.rootPath()).build());
	}

	@Test
	public void testGetJobsByIdDataFetcher() throws Exception {
		DataFetcher jobDataFetcher = graphQLDataFetchers.getJobsByIdDataFetcher();
		Mockito.when(environment.getArgument("_id")).thenReturn("1");
		Mockito.when(jobOpeningDao.getJobOpeningsById(1)).thenReturn(new JobOpening());
		CompletableFuture<JobOpening> jobs = (CompletableFuture<JobOpening>) jobDataFetcher.get(environment);
		Assert.assertTrue("Job opening should not be null.", jobs.get() != null);
	}

	@Test
	public void testGetAllJobs() throws Exception {
		DataFetcher jobDataFetcher = graphQLDataFetchers.getAllJobs();
		Mockito.when(jobOpeningDao.getAllJobOpenings()).thenReturn(Arrays.asList(new JobOpening()));
		CompletableFuture<List<JobOpening>> jobsList = (CompletableFuture<List<JobOpening>>) jobDataFetcher
				.get(environment);
		Assert.assertTrue("Job openings  should not be null!!", jobsList != null && !jobsList.get().isEmpty());
	}

}
