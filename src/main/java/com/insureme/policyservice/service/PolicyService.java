package com.insureme.policyservice.service;

import com.insureme.policyservice.model.Policy;
import com.insureme.policyservice.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository repository;

    public Policy createPolicy(Policy policy) {
        return repository.save(policy);
    }

    public Policy updatePolicy(Long id, Policy updated) {
        Optional<Policy> existing = repository.findById(id);
        if (existing.isPresent()) {
            Policy p = existing.get();
            p.setPolicyName(updated.getPolicyName());
            p.setPolicyHolder(updated.getPolicyHolder());
            p.setPremium(updated.getPremium());
            p.setPolicyType(updated.getPolicyType());
            return repository.save(p);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Policy not found: " + id);
        }
    }

    public Policy viewPolicy(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Policy not found: " + id));
    }

    public List<Policy> getAllPolicies() {
        return repository.findAll();
    }

    public void deletePolicy(Long id) {
        repository.deleteById(id);
    }
}

