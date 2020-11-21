package com.sift.consultancy.school.schoolData.Service;

import com.sift.consultancy.school.schoolData.Model.SchoolInfo;
import com.sift.consultancy.school.schoolData.Model.SchoolWise;
import com.sift.consultancy.school.schoolData.Model.StateWise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    private SchoolInfo schoolInfo;

    public ArrayList<SchoolInfo> schoolList = new ArrayList<SchoolInfo>();

    @PostConstruct
    public void init() {
        String line = "";

        try {

            boolean firstLine = true;
            BufferedReader br = new BufferedReader(new FileReader("src/assets/sl051bai.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] sch;
                sch = line.split(",");
                if (firstLine) {
                        firstLine = false;
                        continue;
                 }
                SchoolInfo schoolInfo = new SchoolInfo();
                schoolInfo.setNCESSCH(Long.parseLong(sch[0]));
                schoolInfo.setLeaID(Long.parseLong(sch[1]));
                schoolInfo.setLeaName(sch[2]);
                try{schoolInfo.setSchoolName(sch[3]);}catch (Exception e){schoolInfo.setSchoolName("");}
                try{schoolInfo.setCity(sch[4]);}catch (Exception e){schoolInfo.setCity("");}
                try{schoolInfo.setState(sch[5]);}catch (Exception e){schoolInfo.setState("");};
                try{schoolInfo.setLatitude(Float.parseFloat(sch[6]));}catch (Exception e){schoolInfo.setLatitude(0);}
                try{schoolInfo.setLongitude(Float.parseFloat(sch[7]));}catch (Exception e){schoolInfo.setLongitude(0);}
                try{schoolInfo.setMetroCentric(Integer.parseInt(sch[8]));}catch (Exception e){schoolInfo.setMetroCentric(0);}
                try{schoolInfo.setUrbanCentric(Integer.parseInt(sch[9]));}catch (Exception e){schoolInfo.setUrbanCentric(0);}
                try{schoolInfo.setStatus(Integer.parseInt(sch[10]));}catch (Exception e){schoolInfo.setStatus(0);}
                schoolList.add(schoolInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        catch (Exception e){
           e.printStackTrace();
        }

    }


    public long getTotalSchool(){
        return schoolList.size();
    }

    public Set<String> getUniqueCities() {
        HashSet<String> uniqueCities = new HashSet<String>();
        schoolList.forEach( data -> uniqueCities.add(data.getCity()));
        System.out.println(uniqueCities.size());
        return uniqueCities;
    }


    public List<StateWise> getStateWise() {
        List<StateWise> stateWiseList = new ArrayList<>();
        Set<String> uniqueStates = new HashSet<String>();
        schoolList.forEach(data -> uniqueStates.add(data.getState()));
        uniqueStates.forEach(state ->{
            StateWise stateWise = new StateWise();
            stateWise.setState(state);
            stateWise.setCount(Collections.frequency(schoolList,state));
            stateWiseList.add(stateWise);
        } );
        stateWiseList.sort((s1, s2) -> (int) (s1.getCount() - s2.getCount()));
        return stateWiseList;
    }

    public String getMostSchool() {
        List<SchoolWise> schoolWiseList = new ArrayList<>();
        HashSet<String> uniqueCities = new HashSet<String>();
        schoolList.forEach( data -> uniqueCities.add(data.getCity()));
        uniqueCities.forEach(state ->{
            SchoolWise schoolWise = new SchoolWise();
            schoolWise.setCity(state);
            schoolWise.setCount(Collections.frequency(schoolList,state));
            schoolWiseList.add(schoolWise);
        } );

        schoolWiseList.sort((s1, s2) -> (int) (s1.getCount() - s2.getCount()));

        SchoolWise topSchool = schoolWiseList.get(0);

        return  "CityName="+topSchool.getCity()+"  "+"count="+topSchool.getCount();
    }




}
