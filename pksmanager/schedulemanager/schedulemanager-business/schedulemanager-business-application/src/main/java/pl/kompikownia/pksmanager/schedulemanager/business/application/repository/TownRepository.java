package pl.kompikownia.pksmanager.schedulemanager.business.application.repository;


import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.TownProjection;

import java.util.List;

public interface TownRepository { //TODO: Replace TownEntity with TownProjection

    TownProjection save(TownProjection town);

    TownProjection findById(Long id);

    List<TownProjection> findAll();

}
