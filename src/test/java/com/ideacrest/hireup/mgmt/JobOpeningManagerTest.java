package com.ideacrest.hireup.mgmt;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ideacrest.app.validator.BeanValidatorUtility;
import com.ideacrest.hireup.bean.JobOpening;
import com.ideacrest.hireup.dao.JobOpeningDao;

public class JobOpeningManagerTest {

	@InjectMocks
	private JobOpeningManager jobOpeningManager;

	@Mock
	private JobOpeningDao jobOpeningDao;

	@Mock
	private BeanValidatorUtility beanValidatorUtility;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetJobOpeningsWithInvalidId() {
		Mockito.when(jobOpeningDao.getJobOpeningsById(Mockito.anyLong())).thenReturn(null);
		Response result = jobOpeningManager.getJobOpeningsById(12);
		int expectedStatusCode = Status.NOT_FOUND.getStatusCode();
		int actualStatusCode = result.getStatus();
		Assert.assertTrue(
				"Invalid status code.\nExpected: " + expectedStatusCode + "\n Actual code: " + actualStatusCode,
				expectedStatusCode == actualStatusCode);
		Assert.assertTrue("Response entity should not be null.", result.getEntity() != null);
	}

	@Test
	public void testGetJobOpeningByIdWithValidId() {
		Mockito.when(jobOpeningDao.getJobOpeningsById(Mockito.anyLong())).thenReturn(new JobOpening());
		Response result = jobOpeningManager.getJobOpeningsById(1);
		int expectedStatusCode = Status.OK.getStatusCode();
		int actualStatusCode = result.getStatus();
		Assert.assertTrue(
				"Invalid status code.\nExpected: " + expectedStatusCode + "\n Actual code: " + actualStatusCode,
				expectedStatusCode == actualStatusCode);
		Assert.assertTrue("Response entity should not be null.", result.getEntity() != null);
	}

	@Test
	public void testGetAllJobOpenings() {
		Mockito.when(jobOpeningDao.getAllJobOpenings()).thenReturn(Arrays.asList(new JobOpening()));
		Response result = jobOpeningManager.getAllJobOpenings();
		int expectedStatusCode = Status.OK.getStatusCode();
		int actualStatusCode = result.getStatus();
		Assert.assertTrue(
				"Invalid status code.\nExpected: " + expectedStatusCode + "\n Actual code: " + actualStatusCode,
				expectedStatusCode == actualStatusCode);
		Assert.assertTrue("Response entity should not be null.", result.getEntity() != null);
	}

	@Test
	public void testAddJobOpeningWithInvalidPayload() {
		Mockito.when(beanValidatorUtility.validateBean(Mockito.any(JobOpening.class)))
				.thenReturn(Arrays.asList("Invalid name"));
		Response result = jobOpeningManager.addJobOpenings(new JobOpening());
		int expectedStatusCode = Status.BAD_REQUEST.getStatusCode();
		int actualStatusCode = result.getStatus();
		Assert.assertTrue(
				"Invalid status code.\nExpected: " + expectedStatusCode + "\n Actual code: " + actualStatusCode,
				expectedStatusCode == actualStatusCode);
		Assert.assertTrue("Response entity should not be null.", result.getEntity() != null);
	}

	@Test
	public void testAddJobOpeningWithExistingId() {
		Mockito.when(beanValidatorUtility.validateBean(Mockito.any(JobOpening.class)))
				.thenReturn(new ArrayList<String>());
		Mockito.when(jobOpeningDao.getJobOpeningsById(Mockito.anyInt())).thenReturn(new JobOpening());
		Response result = jobOpeningManager.addJobOpenings(new JobOpening());
		int expectedStatusCode = Status.BAD_REQUEST.getStatusCode();
		int actualStatusCode = result.getStatus();
		Assert.assertTrue(
				"Invalid status code.\nExpected: " + expectedStatusCode + "\n Actual code: " + actualStatusCode,
				expectedStatusCode == actualStatusCode);
		Assert.assertTrue("Response entity should not be null.", result.getEntity() != null);
	}

	@Test
	public void testAddJobOpeningWithValidId() {
		Mockito.when(beanValidatorUtility.validateBean(Mockito.any(JobOpening.class)))
				.thenReturn(new ArrayList<String>());
		Mockito.when(jobOpeningDao.getJobOpeningsById(Mockito.anyInt())).thenReturn(null);
		Mockito.doNothing().when(jobOpeningDao).insertOne(Mockito.any(JobOpening.class));
		Response result = jobOpeningManager.addJobOpenings(new JobOpening());
		int expectedStatusCode = Status.OK.getStatusCode();
		int actualStatusCode = result.getStatus();
		Assert.assertTrue(
				"Invalid status code.\nExpected: " + expectedStatusCode + "\n Actual code: " + actualStatusCode,
				expectedStatusCode == actualStatusCode);
		Assert.assertTrue("Response entity should not be null.", result.getEntity() != null);
	}

	@Test
	public void testUpdateJobOpeningWithInvalidPayload() {
		Mockito.when(beanValidatorUtility.validateBean(Mockito.any(JobOpening.class)))
				.thenReturn(Arrays.asList("Invalid name"));
		Response result = jobOpeningManager.updateJobOpenings(new JobOpening());
		int expectedStatusCode = Status.BAD_REQUEST.getStatusCode();
		int actualStatusCode = result.getStatus();
		Assert.assertTrue(
				"Invalid status code.\nExpected: " + expectedStatusCode + "\n Actual code: " + actualStatusCode,
				expectedStatusCode == actualStatusCode);
		Assert.assertTrue("Response entity should not be null.", result.getEntity() != null);
	}

	@Test
	public void testUpdateJobOpeningWithNonExistingId() {
		Mockito.when(beanValidatorUtility.validateBean(Mockito.any(JobOpening.class)))
				.thenReturn(new ArrayList<String>());
		Mockito.when(jobOpeningDao.getJobOpeningsById(Mockito.anyInt())).thenReturn(null);
		Response result = jobOpeningManager.updateJobOpenings(new JobOpening());
		int expectedStatusCode = Status.NOT_FOUND.getStatusCode();
		int actualStatusCode = result.getStatus();
		Assert.assertTrue(
				"Invalid status code.\nExpected: " + expectedStatusCode + "\n Actual code: " + actualStatusCode,
				expectedStatusCode == actualStatusCode);
		Assert.assertTrue("Response entity should not be null.", result.getEntity() != null);
	}

	@Test
	public void testUpdateJobOpeningWithValidId() {
		Mockito.when(beanValidatorUtility.validateBean(Mockito.any(JobOpening.class)))
				.thenReturn(new ArrayList<String>());
		Mockito.when(jobOpeningDao.getJobOpeningsById(Mockito.anyInt())).thenReturn(new JobOpening());
		Mockito.doNothing().when(jobOpeningDao).updateOne(Mockito.any(JobOpening.class), Mockito.any());
		Response result = jobOpeningManager.updateJobOpenings(new JobOpening());
		int expectedStatusCode = Status.OK.getStatusCode();
		int actualStatusCode = result.getStatus();
		Assert.assertTrue(
				"Invalid status code.\nExpected: " + expectedStatusCode + "\n Actual code: " + actualStatusCode,
				expectedStatusCode == actualStatusCode);
		Assert.assertTrue("Response entity should not be null.", result.getEntity() != null);
	}

}
