package pl.kompikownia.pksmanager.busmanager.api.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class SimpleBusRequest {
    private String model;
    private String registrationNumber;
}
