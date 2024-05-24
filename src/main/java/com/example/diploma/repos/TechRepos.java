package com.example.diploma.repos;

import com.example.diploma.Entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechRepos extends JpaRepository <Technology,Integer> {

}
