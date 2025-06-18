package com.charter.customer.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void calculateRewardPoints() {
        Assertions.assertEquals(90L, Utils.calculateRewardsPoints(120d));
        Assertions.assertEquals(25L, Utils.calculateRewardsPoints(75d));
        Assertions.assertEquals(450L, Utils.calculateRewardsPoints(300d));
        Assertions.assertEquals(150L, Utils.calculateRewardsPoints(150d));
        Assertions.assertEquals(0L, Utils.calculateRewardsPoints(25d));
    }
}