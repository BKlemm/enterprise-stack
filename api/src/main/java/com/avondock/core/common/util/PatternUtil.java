package com.avondock.core.common.util;

public class PatternUtil {
    public static final String UUID = "^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$";
    public static final String LATITUDE = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)$";
    public static final String LONGITUDE = "[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$";
}
