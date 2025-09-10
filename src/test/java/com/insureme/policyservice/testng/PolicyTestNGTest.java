package com.insureme.policyservice.testng;

import com.insureme.policyservice.model.Policy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PolicyTestNGTest {

    private Policy policy;

    @BeforeClass
    public void setUp() {
        policy = new Policy();
        policy.setPolicyId(1001L);
        policy.setPolicyName("Home Secure");
        policy.setPolicyHolder("Alice Johnson");
        policy.setPremium(450.0);
        policy.setPolicyType("Home");
    }

    @Test
    public void testPremiumPositive() {
        Assert.assertTrue(policy.getPremium() > 0, "Premium must be positive");
    }

    @Test
    public void testPolicyNameNotEmpty() {
        Assert.assertNotNull(policy.getPolicyName(), "Policy name should not be null");
    }

    @Test
    public void testPolicyType() {
        Assert.assertEquals(policy.getPolicyType(), "Home", "Policy type should match");
    }
}

