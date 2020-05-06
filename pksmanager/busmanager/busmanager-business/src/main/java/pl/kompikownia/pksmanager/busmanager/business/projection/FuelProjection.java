package pl.kompikownia.pksmanager.busmanager.business.projection;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Setter
public class FuelProjection {

    private Long id;
    private Long quantify;
    private Long busId;
    private String type;

}
