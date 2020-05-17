package pl.kompikownia.pksmanager.usermanager.test;

import pl.kompikownia.pksmanager.usermanager.api.response.GetUserListResponse;
import pl.kompikownia.pksmanager.usermanager.api.response.ResponseUserData;
import pl.kompikownia.pksmanager.usermanager.api.response.ResponseWorkerData;

import java.util.List;

public class TestConstants {
    public static final String USER_NAME = "Jan";
    public static final String USER_SURNAME = "Kowalski";
    public static final String USER_LOGIN = "jankow2394";
    public static final String USER_PASSWORD = "5554432";
    public static final String USER_EMAIL = "jankowalski@o2.pl";
    public static final String USER_DRIVER_LICENSE_NUMBER = "21/21/19384";
    public static final String USER_PERSONAL_ID_NUMBER = "CVF544344";
    public static final String USER_PESEL = "12312042494";

    public static final String WORKER_LOGIN = "adamnowak";
    public static final String WORKER_NAME = "Adam";
    public static final String WORKER_SURNAME = "Nowak";
    public static final String WORKER_EMAIL = "adamnowak@wp.pl";
    public static final String WORKER_LICENSE_NUMBER = "21/21/12345";
    public static final String WORKER_PESEL = "97041812122";
    public static final String WORKER_PERSON_ID = "DKF291829";

    public static List<ResponseUserData> getResponseUserDataList() {
        return List.of(ResponseUserData.builder()
                .name(USER_NAME)
                .surname(USER_SURNAME)
                .email("jankowalski@wp.pl")
                .login("jankowalski")
                .isWorker(false)
                .build(),
                ResponseUserData.builder()
                .name(WORKER_NAME)
                .surname(WORKER_SURNAME)
                .email(WORKER_EMAIL)
                .login(WORKER_LOGIN)
                .isWorker(true)
                .build());
    }

    public static ResponseWorkerData getResponseWorkerData() {
        return ResponseWorkerData.builder()
                .name(WORKER_NAME)
                .surname(WORKER_SURNAME)
                .driverLicenseNumber(WORKER_LICENSE_NUMBER)
                .email(WORKER_EMAIL)
                .login(WORKER_LOGIN)
                .personIdNumber(WORKER_PERSON_ID)
                .pesel(WORKER_PESEL)
                .build();
    }
}
