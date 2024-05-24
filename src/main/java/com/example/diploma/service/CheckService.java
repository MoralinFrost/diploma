package com.example.diploma.service;

import com.example.diploma.Entity.Check;
import com.example.diploma.Entity.User;
import com.example.diploma.repos.CheckRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CheckService {

    @Autowired
    private CheckRepos checkRepos;

    public List<Check> findAll(){
        return checkRepos.findAll();
    }

    public Check addCheck(User employee, User interviewer, LocalDate chechDate, boolean status){
        Check check = new Check();
        check.setEmployee(employee);
        check.setInterviewer(interviewer);
        check.setCheckDate(chechDate);
        check.setStatus(status);
        return checkRepos.save(check);
    }

    public Optional<Check> findCheckById(Integer id){
        return checkRepos.findById(id);
    }

    public Check updateCheck(Integer id, User employee, User interviewer, LocalDate chechDate, boolean status, String feedback){
        Optional<Check> check = checkRepos.findById(id);
        if(check.isPresent()){
            Check check1 = check.get();
            check1.setEmployee(employee);
            check1.setInterviewer(interviewer);
            check1.setCheckDate(chechDate);
            check1.setStatus(status);
            check1.setFeedback(feedback);
            return checkRepos.save(check1);
        }
        return null;
    }

    public void deleteCheckById(Integer id){
        checkRepos.deleteById(id);
    }


}
