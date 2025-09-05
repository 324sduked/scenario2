package utils;

import java.util.List;

import static utils.RandomUtils.randomString;

public class TestStrings {

    public static final String validLoginUsername = "asd";
    public static final String validLoginPassword = "asd";

    public static final String validSignInUsername = randomString(32);
    public static final String validSignInPassword = randomString(32);

    public static final String validContactEmail = "testContactEmail@gmail.com";
    public static final String validContactName = "TestContactName";

    public static final String validMessage = "message";

    public static final List<String> expectedLaptopsList = List.of("Dell", "MacBook", "Sony");
    public static final List<String> expectedMonitorsList = List.of("Monitor", "Full HD", "Apple");

    public static final String macBookAir = "MacBook air";
    public static final String appleMonitor24 = "Apple monitor 24";

    public static final String name = "TestName";
    public static final String country = "TestCountry";
    public static final String city = "TestCity";
    public static final String card = "TestCard";
    public static final String month = "February";
    public static final String year = "2000";

}
