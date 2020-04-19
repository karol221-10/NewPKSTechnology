package pl.kompikownia.pksmanager.timetable.business.repository;

import pl.kompikownia.pksmanager.timetable.infrastructure.entity.TownEntity;

import java.util.List;

public interface TownEntityRepository { //TODO: Replace TownEntity with TownProjection

    public TownEntity save(TownEntity town);

    public List<TownEntity> findAll();

    public List<TownEntity> findByName(String name);

    public void deleteById(Long id);

    void deleteAll();
    
}
