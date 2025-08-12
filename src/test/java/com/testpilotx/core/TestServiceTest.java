package com.testpilotx.core;

import com.testpilotx.core.model.TestCase;
import com.testpilotx.core.model.TestResult;
import com.testpilotx.core.service.TestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TestServiceTest {

    private TestService testService;

    @BeforeEach
    public void setUp() {
        testService = new TestService();
    }

    @Test
    public void testCreateTest() {
        TestCase testCase = new TestCase();
        testCase.setName("Sample Test");
        testCase.setDescription("A simple test case");
        testCase.setScript("assert true");

        TestCase created = testService.createTest(testCase);

        assertNotNull(created.getId());
        assertEquals("Sample Test", created.getName());
    }

    @Test
    public void testRunTest_Passed() {
        TestCase testCase = new TestCase();
        testCase.setName("Passing Test");
        testCase.setScript("assert true");
        TestCase created = testService.createTest(testCase);

        TestResult result = testService.runTest(created.getId());

        assertTrue(result.isPassed());
        assertEquals("Test passed.", result.getOutput());
    }

    @Test
    public void testRunTest_Failed() {
        TestCase testCase = new TestCase();
        testCase.setName("Failing Test");
        testCase.setScript("something else");
        TestCase created = testService.createTest(testCase);

        TestResult result = testService.runTest(created.getId());

        assertFalse(result.isPassed());
        assertEquals("Assertion failed.", result.getOutput());
    }

    @Test
    public void testRunTest_NotFound() {
        TestResult result = testService.runTest("non-existent-id");

        assertFalse(result.isPassed());
        assertEquals("Test not found", result.getOutput());
    }
    
}
