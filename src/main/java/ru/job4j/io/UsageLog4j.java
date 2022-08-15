package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    public static void main(String[] args) {
        byte byteValue = 127;
        short shortValue = 32767;
        int intValue = 2147483647;
        long longValue = 2147483649L;
        float floatValue = 3.14F;
        double doubleValue = 12.4567D;
        boolean bool = true;
        char charValue = 'b';
        LOG.debug("examples of variable values: byte: {}, short: {}, \n int: {}, long: {}," +
                " float: {}, double: {}, boolean: {}, char: {}", byteValue, shortValue,
                intValue, longValue, floatValue, doubleValue, bool, charValue);
    }
}
