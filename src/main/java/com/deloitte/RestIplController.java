package com.deloitte;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nagesingh on 4/29/2017.
 */
@RestController
public class RestIplController {

    @Autowired
    private DatabaseDao databaseDao;

    private List<IplStat> all = null;

    @PostConstruct
    public void after(){
        all = databaseDao.findAll();
    }

    @RequestMapping(value = "/getMatchDetailsBySeason", method = RequestMethod.GET)
    public String getMatchDetailsBySeason(@RequestParam String season){
        List<IplStat> allIPLData = new ArrayList<>();
        all.forEach( iplStat -> {
            if (season.equals(iplStat.getSeason())){
                allIPLData.add(iplStat);
            }
        });
        Gson gson = new GsonBuilder().create();
        return gson.toJson(allIPLData);
    }

    @RequestMapping(value = "/getMatchesNumberBySeason", method = RequestMethod.GET)
    public String getMatchesNumberBySeason(){
        Map<String, Integer> matchesNumberBySeason = new HashMap<>();
        all.forEach( iplStat -> {
            String key = iplStat.getSeason();
            if (matchesNumberBySeason.containsKey(key)) {
                Integer integer = matchesNumberBySeason.get(key);
                matchesNumberBySeason.put(key, integer + 1);
            } else {
                matchesNumberBySeason.put(key, 1);
            }
        });
        Gson gson = new GsonBuilder().create();
        return gson.toJson(matchesNumberBySeason);
    }

    @RequestMapping(value = "/toseasonPage")
    public ModelAndView seasonPage(@RequestParam String season, ModelAndView modelAndView){
        modelAndView.addObject("numberOfMatchesPerSeason", 5);
        modelAndView.addObject("seasons", season);
        modelAndView.setViewName("seasonPage.html");

        return modelAndView;
    }
}
