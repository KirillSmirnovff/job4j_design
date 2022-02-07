package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 30;
        byte number = 5;
        short count = 40;
        long distance = 99999;
        float fractional = 8.55f;
        double weight = 70.55;
        char letter = 'K';
        boolean condition = true;
        LOG.debug("Testing output: age {}, number {}, count {}, distance {}, fractional {}, weight {}, letter {}, condition {}",
                age, number, count, distance, fractional, weight, letter, condition);
    }
}