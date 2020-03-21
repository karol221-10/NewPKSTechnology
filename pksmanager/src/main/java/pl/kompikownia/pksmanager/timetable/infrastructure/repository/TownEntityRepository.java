package pl.kompikownia.pksmanager.timetable.infrastructure.repository;

import pl.kompikownia.pksmanager.timetable.infrastructure.entity.TownEntity;

import java.util.List;

public interface TownEntityRepository {

    public TownEntity save(TownEntity town);

    public List<TownEntity> findAll();

    public List<TownEntity> findByName(String name);

    public void deleteById(Long id);
    
}
