package com.testpilotx.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testpilotx.core.model.TestCase;
import com.testpilotx.core.model.TestResult;
import com.testpilotx.core.service.TestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TestController.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService testService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllTests() throws Exception {
        TestCase tc1 = new TestCase();
        tc1.setId("1");
        tc1.setName("Test 1");
        tc1.setDescription("Desc 1");
        tc1.setScript("assert true");

        TestCase tc2 = new TestCase();
        tc2.setId("2");
        tc2.setName("Test 2");
        tc2.setDescription("Desc 2");
        tc2.setScript("assert false");

        List<TestCase> mockTests = Arrays.asList(tc1, tc2);
        Mockito.when(testService.getAllTests()).thenReturn(mockTests);

        mockMvc.perform(get("/api/tests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Test 1")))
                .andExpect(jsonPath("$[1].name", is("Test 2")));
    }

    @Test
    void testCreateTest() throws Exception {
        TestCase tc = new TestCase();
        tc.setId(UUID.randomUUID().toString());
        tc.setName("New Test");
        tc.setDescription("New Description");
        tc.setScript("assert something");

        Mockito.when(testService.createTest(any(TestCase.class))).thenReturn(tc);

        mockMvc.perform(post("/api/tests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tc)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("New Test")))
                .andExpect(jsonPath("$.description", is("New Description")))
                .andExpect(jsonPath("$.script", is("assert something")));
    }

    @Test
    void testRunTest() throws Exception {
        TestResult result = new TestResult("123", true, "Test passed.");
        Mockito.when(testService.runTest(eq("123"))).thenReturn(result);

        mockMvc.perform(post("/api/tests/run/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.testId", is("123")))
                .andExpect(jsonPath("$.passed", is(true)))
                .andExpect(jsonPath("$.output", is("Test passed.")));
    }

    @Test
    void testGetResults() throws Exception {
        List<TestResult> mockResults = Arrays.asList(
                new TestResult("1", true, "OK"),
                new TestResult("2", false, "Fail")
        );

        Mockito.when(testService.getAllResults()).thenReturn(mockResults);

        mockMvc.perform(get("/api/tests/results"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].passed", is(true)))
                .andExpect(jsonPath("$[1].passed", is(false)));
    }
}
