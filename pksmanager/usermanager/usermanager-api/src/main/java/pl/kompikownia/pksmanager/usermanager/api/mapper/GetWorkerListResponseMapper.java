package pl.kompikownia.pksmanager.usermanager.api.mapper;

import lombok.val;
import pl.kompikownia.pksmanager.usermanager.api.response.GetWorkerListResponse;
import pl.kompikownia.pksmanager.usermanager.api.response.ResponseWorkerData;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserData;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserWithTypeData;
import pl.kompikownia.pksmanager.usermanager.business.projection.WorkerData;

import java.util.List;
import java.util.stream.Collectors;

public class GetWorkerListResponseMapper {

    public static GetWorkerListResponse map(List<WorkerData> workers, List<UserWithTypeData> users) {
        return new GetWorkerListResponse(workers.stream()
                .map(worker -> mapToResponseWorkerData(worker, users))
                .collect(Collectors.toList()));
    }

    private static ResponseWorkerData mapToResponseWorkerData(WorkerData worker, List<UserWithTypeData> users) {
        val user = users.stream()
                .filter(userData -> userData.getUserData().getId().equals(worker.getUserId().toString()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot map user data"));
        return ResponseWorkerData.builder()
                .name(user.getUserData().getName())
                .surname(user.getUserData().getSurname())
                .email(user.getUserData().getEmail())
                .login(user.getLogin())
                .driverLicenseNumber(worker.getDriverLicenseNumber())
                .personIdNumber(worker.getPersonIdNumber())
                .pesel(worker.getPesel())
                .build();
    }
}
