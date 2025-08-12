package com.testpilotx.core.service;

import com.testpilotx.core.model.TestCase;
import com.testpilotx.core.model.TestResult;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestService {
    private final Map<String, TestCase> testCases = new HashMap<>();
    private final List<TestResult> results = new ArrayList<>();

    public List<TestCase> getAllTests() {
        return new ArrayList<>(testCases.values());
    }

    public TestCase createTest(TestCase testCase) {
        testCases.put(testCase.getId(), testCase);
        return testCase;
    }

    public TestResult runTest(String id) {
        TestCase testCase = testCases.get(id);
        if (testCase == null) return new TestResult(id, false, "Test not found");

        boolean passed = testCase.getScript().contains("assert");
        String output = passed ? "Test passed." : "Assertion failed.";
        TestResult result = new TestResult(id, passed, output);
        results.add(result);
        return result;
    }

    public List<TestResult> getAllResults() {
        return results;
    }
}
