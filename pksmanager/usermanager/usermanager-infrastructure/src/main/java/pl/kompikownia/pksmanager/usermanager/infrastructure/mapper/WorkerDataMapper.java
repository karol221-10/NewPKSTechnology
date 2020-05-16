package pl.kompikownia.pksmanager.usermanager.infrastructure.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.usermanager.business.projection.WorkerData;
import pl.kompikownia.pksmanager.usermanager.infrastructure.entity.WorkerEntity;

@UtilityClass
public class WorkerDataMapper {

    public static WorkerData map(WorkerEntity workerEntity) {
        return WorkerData.builder()
                .workerId(workerEntity.getId())
                .userId(workerEntity.getUserEntity().getId())
                .driverLicenseNumber(workerEntity.getDriverLicenseNumber())
                .personIdNumber(workerEntity.getPersonIdNumber())
                .pesel(workerEntity.getPesel())
                .build();
    }
}
