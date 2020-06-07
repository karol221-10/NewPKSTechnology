package pl.kompikownia.pksmanager.busmanager.business.projection;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Setter
public class SimpleBusProjection {
    private Long id;
    private String model;
    private String registrationNumber;
}
