package pl.kompikownia.pksmanager.usermanager.api.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class GetWorkerListResponse {

    private List<ResponseWorkerData> workers;
}
