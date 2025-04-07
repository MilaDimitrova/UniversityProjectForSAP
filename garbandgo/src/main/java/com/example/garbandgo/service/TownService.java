package com.example.garbandgo.service;
import com.example.garbandgo.entities.Town;
import com.example.garbandgo.repositories.TownRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TownService {
    private final TownRepository townRepository;


    public TownService(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    public List<Town> getTownRepository() {
        return townRepository.findAll();
    }

    public void saveTown(Town town) {
        townRepository.save(town);
    }
}
