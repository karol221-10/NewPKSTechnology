package pl.kompikownia.pksmanager.timetable.business.service.queryhandler;

import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.timetable.api.response.ScheduleView;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.GetScheduleListQuery;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.BusStopEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.ScheduleEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.TownEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.repository.jpa.ScheduleEntityRepositoryImpl;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class GetScheduleListQueryHandler extends QueryHandler<List<ScheduleView>, GetScheduleListQuery> {

    @Inject
    ScheduleEntityRepositoryImpl repository = new ScheduleEntityRepositoryImpl();


    public List<ScheduleView> convertToView(List<ScheduleEntity> scheduleEntities){


        List<ScheduleView> result = scheduleEntities.stream().map(temp-> {
            ScheduleView obj = ScheduleView.builder()
                    .id(temp.getId())
                    .busId(temp.getBusId())
                    .busStopEntities(temp.getBusStopEntities())
                    .workerId(temp.getWorkerId())
                    .isActive(temp.isActive())
                    .price(temp.getPrice())
                    .build();
            return obj;
        }).collect(Collectors.toList());
        return result;
    }


    public List<ScheduleEntity> shortList(Long id1, Long id2){
        List<ScheduleEntity> temp = repository.findCourseByTownId(id1,id2);
        List<ScheduleEntity> scheduleToTownOne = null;
        List<ScheduleEntity> finalList = null;

        for (ScheduleEntity se: temp
             ) {
            BusStopEntity heh = se.getBusStopEntities().stream().filter((bs) -> id1.equals(bs.getTown().getId())).findAny().orElse(null);
          se.setBusStopEntities(se.getBusStopEntities().subList(heh.getId().intValue()+1,se.getBusStopEntities().size()-1));
          scheduleToTownOne.add(se);
        }

      for (ScheduleEntity se: temp){
          BusStopEntity heh = se.getBusStopEntities().stream().filter((bs) -> id2.equals(bs.getTown().getId())).findAny().orElse(null);
          if(se.getBusStopEntities().contains(heh)){
              finalList.add(se);
          }
      }
        return finalList;
    }




    @Override
    public List<ScheduleView> handle(GetScheduleListQuery query) {
        return convertToView(shortList(id1,id2));
    }
}
