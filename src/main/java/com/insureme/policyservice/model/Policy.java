package com.insureme.policyservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "policy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

    @Id
    private Long policyId;

    @Column(name = "policy_name")
    private String policyName;

    @Column(name = "policy_holder")
    private String policyHolder;

    @Column(name = "premium")
    private Double premium;

    @Column(name = "policy_type")
    private String policyType;
}

