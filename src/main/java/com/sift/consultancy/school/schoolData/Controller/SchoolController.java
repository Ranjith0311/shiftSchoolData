package com.sift.consultancy.school.schoolData.Controller;

import com.sift.consultancy.school.schoolData.Model.SchoolInfo;
import com.sift.consultancy.school.schoolData.Model.StateWise;
import com.sift.consultancy.school.schoolData.Service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class SchoolController {

    @Autowired
    private SchoolInfo schoolInfo;
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/total")
    public long getTotalSchool(){
        return schoolService.getTotalSchool();
    }

    @GetMapping("/uniqueCities")
    public Set<String> uniqueCities(){return  schoolService.getUniqueCities();}

    @GetMapping("/eachState")
    public List<StateWise> getSateWise() {return schoolService.getStateWise();}

    @GetMapping("/mostSchool")
    public String getMostSchool() {return schoolService.getMostSchool();}

}
