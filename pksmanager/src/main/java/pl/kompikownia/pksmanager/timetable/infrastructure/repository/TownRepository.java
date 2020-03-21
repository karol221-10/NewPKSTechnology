package pl.kompikownia.pksmanager.timetable.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.Town;

import java.util.List;

public interface TownRepository{

    public Town save(Town town);

    public List<Town> findAll();

    public List<Town> findByName(String name);

    public void deleteById(Long id);
    
}
