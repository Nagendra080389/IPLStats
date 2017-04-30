package com.deloitte;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nagesingh on 4/29/2017.
 */
@Component
public class IplDoMapper {

    public IplStat setAttributes(String[] line, List<String[]> allMatchdetails){

        List<String[]> filteredList = filterMatchDetailsData(allMatchdetails, line[0]);
        List<MatchDetails> matchDetailsList = convertArraytoList(filteredList);
        IplStat iplStat = new IplStat();
        iplStat.set_id(line[0]);
        iplStat.setSeason(line[1]);
        iplStat.setCity(line[2]);
        try {
            iplStat.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(line[3]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        iplStat.setTeam1(line[4]);
        iplStat.setTeam2(line[5]);
        iplStat.setToss_winner(line[6]);
        iplStat.setToss_decision(line[7]);
        iplStat.setResult(line[8]);
        iplStat.setDl_applied(setBoolean(line[9]));
        iplStat.setWinner(line[10]);
        iplStat.setWin_by_runs(new Integer(line[11]));
        iplStat.setWin_by_wickets(new Integer(line[12]));
        iplStat.setPlayer_of_match(line[13]);
        iplStat.setVenue(line[14]);
        iplStat.setUmpire1(line[15]);
        iplStat.setUmpire2(line[16]);
        iplStat.setUmpire3(line[17]);
        iplStat.setMatchDetails(matchDetailsList);

        return iplStat;
    }

    private List<MatchDetails> convertArraytoList(List<String[]> filteredList) {
        List<MatchDetails> matchDetailsList = new ArrayList<>();
        MatchDetails matchDetails;
        for (String[] details : filteredList) {
            matchDetails = new MatchDetails();
            matchDetails.setMatch_id(details[0]);
            matchDetails.setInning(new Integer(details[1]));
            matchDetails.setBatting_team(details[2]);
            matchDetails.setBowling_team(details[3]);
            matchDetails.setOver(new Integer(details[4]));
            matchDetails.setBall(new Integer(details[5]));
            matchDetails.setBatsman(details[6]);
            matchDetails.setNon_striker(details[7]);
            matchDetails.setBowler(details[8]);
            matchDetails.setIs_super_over(new Integer(details[9]));
            matchDetails.setWide_runs(new Integer(details[10]));
            matchDetails.setBye_runs(new Integer(details[11]));
            matchDetails.setLegbye_runs(new Integer(details[12]));
            matchDetails.setNoball_runs(new Integer(details[13]));
            matchDetails.setPenalty_runs(new Integer(details[14]));
            matchDetails.setBatsman_runs(new Integer(details[15]));
            matchDetails.setExtra_runs(new Integer(details[16]));
            matchDetails.setTotal_runs(new Integer(details[17]));
            matchDetails.setPlayer_dismissed(details[18]);
            matchDetails.setDismissal_kind(details[19]);
            matchDetails.setFielder(details[20]);
            matchDetailsList.add(matchDetails);
        }

        return matchDetailsList;
    }

    private Boolean setBoolean(String s) {
        if(s.equals("0")){
            return Boolean.FALSE;
        }else {
            return Boolean.TRUE;
        }
    }

    private List<String[]> filterMatchDetailsData(List<String[]> allMatchdetails, String matchId) {

        List<String[]> filteredResult = new ArrayList<>();
        for (String[] allMatchdetail : allMatchdetails) {
            if(allMatchdetail[0].equals(matchId)){
                filteredResult.add(allMatchdetail);
            }
        }

        return filteredResult;
    }

}
