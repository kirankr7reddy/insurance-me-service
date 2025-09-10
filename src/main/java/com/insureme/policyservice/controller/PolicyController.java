package com.insureme.policyservice.controller;

import com.insureme.policyservice.model.Policy;
import com.insureme.policyservice.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService service;

    @PostMapping("/createPolicy")
    public Policy createPolicy(@RequestBody Policy policy) {
        return service.createPolicy(policy);
    }

    @PutMapping("/updatePolicy/{policyId}")
    public Policy updatePolicy(@PathVariable Long policyId, @RequestBody Policy policy) {
        return service.updatePolicy(policyId, policy);
    }

    @GetMapping("/viewPolicy/{policyId}")
    public Policy viewPolicy(@PathVariable Long policyId) {
        return service.viewPolicy(policyId);
    }

    @GetMapping
    public List<Policy> getAllPolicies() {
        return service.getAllPolicies();
    }

    @DeleteMapping("/deletePolicy/{policyId}")
    public String deletePolicy(@PathVariable Long policyId) {
        service.deletePolicy(policyId);
        return "Policy " + policyId + " deleted successfully";
    }
}

