package com.insureme.policyservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insureme.policyservice.model.Policy;
import com.insureme.policyservice.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PolicyController.class)
public class PolicyControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private PolicyService policyService;

    @BeforeMethod
    public void setup() {
        policyService = mock(PolicyService.class);
    }

    @Test
    public void testCreatePolicy() throws Exception {
        Policy policy = new Policy();
        policy.setPolicyId(1001L);
        policy.setPolicyName("Home Secure");
        policy.setPolicyHolder("Alice Johnson");
        policy.setPremium(450.0);
        policy.setPolicyType("Home");

        when(policyService.createPolicy(any(Policy.class))).thenReturn(policy);

        mockMvc.perform(post("/api/policies/createPolicy")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(policy)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1001))
                .andExpect(jsonPath("$.policyName").value("Home Secure"))
                .andExpect(jsonPath("$.policyHolder").value("Alice Johnson"))
                .andExpect(jsonPath("$.premium").value(450.0))
                .andExpect(jsonPath("$.policyType").value("Home"));

        verify(policyService, times(1)).createPolicy(any(Policy.class));
    }

    @Test
    public void testViewPolicy() throws Exception {
        Policy policy = new Policy();
        policy.setPolicyId(1001L);
        policy.setPolicyName("Home Secure");
        policy.setPolicyHolder("Alice Johnson");
        policy.setPremium(450.0);
        policy.setPolicyType("Home");

        when(policyService.viewPolicy(1001L)).thenReturn(policy);

        mockMvc.perform(get("/api/policies/viewPolicy/1001")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1001))
                .andExpect(jsonPath("$.policyName").value("Home Secure"))
                .andExpect(jsonPath("$.policyHolder").value("Alice Johnson"));

        verify(policyService, times(1)).viewPolicy(1001L);
    }

    @Test
    public void testDeletePolicy() throws Exception {
        doNothing().when(policyService).deletePolicy(1001L);

        mockMvc.perform(delete("/api/policies/deletePolicy/1001"))
                .andExpect(status().isOk())
                .andExpect(content().string("Policy 1001 deleted successfully"));

        verify(policyService, times(1)).deletePolicy(1001L);
    }
}
