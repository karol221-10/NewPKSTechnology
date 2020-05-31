package pl.kompikownia.pksmanager.usermanager.infrastructure.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.busmanager.entity.WorkerEntity;
import pl.kompikownia.pksmanager.usermanager.business.projection.WorkerData;

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
