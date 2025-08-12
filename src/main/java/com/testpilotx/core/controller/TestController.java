package com.testpilotx.core.controller;

import com.testpilotx.core.model.TestCase;
import com.testpilotx.core.model.TestResult;
import com.testpilotx.core.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@Tag(name = "Test API", description = "Operations related to test cases")
public class TestController {

    @Autowired
    private TestService testService;

    @Operation(summary = "List all tests")
    @GetMapping
    public List<TestCase> getAllTests() {
        return testService.getAllTests();
    }

    @Operation(summary = "Create a new test")
    @PostMapping
    public TestCase createTest(@RequestBody TestCase testCase) {
        return testService.createTest(testCase);
    }

    @Operation(summary = "Run a test")
    @PostMapping("/run/{id}")
    public TestResult runTest(@PathVariable String id) {
        return testService.runTest(id);
    }

    @Operation(summary = "Get all test results")
    @GetMapping("/results")
    public List<TestResult> getResults() {
        return testService.getAllResults();
    }
}
