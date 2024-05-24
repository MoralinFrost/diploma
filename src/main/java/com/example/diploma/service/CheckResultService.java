package com.example.diploma.service;

import com.example.diploma.Entity.Check;
import com.example.diploma.Entity.CheckResult;
import com.example.diploma.Entity.Technology;
import com.example.diploma.repos.CheckResultRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckResultService {

    @Autowired
    private CheckResultRepos checkResultRepos;

    public List<CheckResult> getAllCheckResults() {
        return checkResultRepos.findAll();
    }

    public CheckResult addCheckResult(Check check, Technology technology, int mark) {
      CheckResult checkResult = new CheckResult();
      checkResult.setCheck(check);
      checkResult.setTechnology(technology);
      checkResult.setMark(mark);
      return checkResultRepos.save(checkResult);
    }

    public Optional<CheckResult> getCheckResultById(Integer id) {
        return checkResultRepos.findById(id);
    }

    public CheckResult updateCheckResult(Integer id, Check check, Technology technology, int mark) {
        Optional<CheckResult> checkResultOptional = checkResultRepos.findById(id);
        if(checkResultOptional.isPresent()){
            CheckResult checkResult = checkResultOptional.get();

//            if (check != null && !check.equals(checkResult.getCheck())) {
//                checkResult.setCheck(check);
//            }
//            if (technology != null && !technology.equals(checkResult.getTechnology())) {
//                checkResult.setTechnology(technology);
//            }
//            if (mark != checkResult.getMark()) {
//                checkResult.setMark(mark);
//            }



            checkResult.setCheck(check);
            checkResult.setTechnology(technology);
            checkResult.setMark(mark);
            return checkResultRepos.save(checkResult);
        }
        return null;
    }

    public void deleteCheckResult(Integer id) {
        checkResultRepos.deleteById(id);
    }
}
