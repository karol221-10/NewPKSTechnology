package pl.kompikownia.pksmanager.schedulemanager.business.application.repository;


import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.TownProjection;

import java.util.List;

public interface TownRepository { //TODO: Replace TownEntity with TownProjection

    public TownProjection save(TownProjection town);

    public List<TownProjection> findAll();

    public List<TownProjection> findByName(String name);

    public void deleteById(Long id);

    void deleteAll();
    
}
