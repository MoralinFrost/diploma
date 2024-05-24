package com.example.diploma.service;

import com.example.diploma.Entity.Technology;
import com.example.diploma.Entity.TechnologyType;
import com.example.diploma.repos.TechRepos;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class TechnologyService {

    @Autowired
    private TechRepos techRepos;

    public List<Technology> getAllTechnologies() {
        return techRepos.findAll();
    }

    public Technology createTechnology(TechnologyType techType, String techName) {
      Technology technology =new Technology(techType, techName);
      return techRepos.save(technology);
    }

    public Optional<Technology> getTechnologyById(Integer id) {
        return techRepos.findById(id);
    }

    public Technology updateTechnology(Integer id, TechnologyType techType, String techName) {
        Optional<Technology> technology = techRepos.findById(id);;
        if(technology.isPresent()){
            Technology tech = technology.get();
            tech.setTech_type(techType);
            tech.setTech_name(techName);
            return techRepos.save(tech);
        }
        return null;
    }

    public void deleteTechnologyById(Integer id) {
        techRepos.deleteById(id);
    }
}
