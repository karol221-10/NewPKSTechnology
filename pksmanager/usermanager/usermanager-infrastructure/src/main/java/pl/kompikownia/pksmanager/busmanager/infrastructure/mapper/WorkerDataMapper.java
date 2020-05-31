package pl.kompikownia.pksmanager.busmanager.infrastructure.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.WorkerEntity;
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
