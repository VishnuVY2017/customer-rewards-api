package com.charter.customer.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    public static Long calculateRewardsPoints(Double ammount) {
        Double totalPoints = 0d;

        if (ammount > 50 && ammount <= 100) {
            totalPoints = totalPoints + (ammount.longValue() - 50);
        }

        if (ammount > 100) {
            totalPoints = totalPoints + (ammount.longValue() - 100) * 2;
            totalPoints = totalPoints + 50;
        }

        return totalPoints.longValue();
    }
}
