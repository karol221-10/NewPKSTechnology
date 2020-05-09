package pl.kompikownia.pksmanager.security.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Constants {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static final String ACTUAL_DATE_MOCK_STRING = "2020-04-12 11:00:00";
    public static final String TOKEN_EXPIRATION_DATE_MOCK_STRING = "2020-04-12 12:00:00";
    public static final String TEST_USER = "testuser";
    public static final String TEST_PASSWORD = "5554432";
    public static Date ACTUAL_DATE_MOCK;
    public static Date TOKEN_EXPIRATION_DATE_MOCK;
    public static final String TOKEN_TO_TEST = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsInBlcm1pc3Npb25zIjoiVEVTVF9QRVJNSVNTSU9OIiwiaWF0IjoxNTg2NjgyMDAwLCJleHAiOjE1ODY2ODU2MDB9.gZ1XyBrTth9vjd_iqW9i0Z2DVA1zGQAkka6_VUnE_rk";
    public static final String EXPIRED_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsInBlcm1pc3Npb25zIjoiVEVTVF9QRVJNSVNTSU9OIiwiaWF0IjoxNTU1MDU5NjAwLCJleHAiOjE1NTUwNjMyMDB9.b5d8RZVNj26tCWt_Ps0aPlwqWc3El962DktzxLcV4Kg";
    public static final String BAD_SIGNATURE_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsInBlcm1pc3Npb25zIjoiVEVTVF9QRVJNSVNTSU9OIiwiaWF0IjoxNTU1MDU5NjAwLCJleHAiOjE1NTUwNjMyMDB9.b5d8RZVNj26tCWt_Ps0aPlwqWc3ElG62AktDxLcA4Kg";
    public static final String BEARER_TOKEN_TO_TEST = "Bearer "+TOKEN_TO_TEST;
    public static final String BEARER_EXPIRED_TOKEN = "Bearer "+EXPIRED_TOKEN;
    public static final String BEARER_BAD_SIGNATURE_TOKEN = "Bearer "+BAD_SIGNATURE_TOKEN;
    public static final List<String> PERMISSION_LIST = List.of("TEST_PERMISSION");

    static {
        try {
            ACTUAL_DATE_MOCK = DATE_FORMAT.parse(ACTUAL_DATE_MOCK_STRING);
            TOKEN_EXPIRATION_DATE_MOCK = DATE_FORMAT.parse(TOKEN_EXPIRATION_DATE_MOCK_STRING);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
