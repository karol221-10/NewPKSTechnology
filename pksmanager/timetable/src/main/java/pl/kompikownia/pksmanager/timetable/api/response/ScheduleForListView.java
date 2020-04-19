package pl.kompikownia.pksmanager.timetable.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@ToString
@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class ScheduleForListView {

    private long id;
    private long leavingFrom;
    private long leavingTo;
    private LocalDateTime date;
    private int passengers;
    private int numberOfStops;
    private Duration travelTime;
}
