package com.deloitte;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by nagesingh on 4/29/2017.
 */
@Document
public class MatchDetails implements Serializable {

    @Id
    private String match_id;
    private Integer inning;
    private String batting_team;
    private String bowling_team;
    private Integer over;
    private Integer ball;
    private String batsman;
    private String non_striker;
    private String bowler;
    private Integer is_super_over;
    private Integer wide_runs;
    private Integer bye_runs;
    private Integer legbye_runs;
    private Integer noball_runs;
    private Integer penalty_runs;
    private Integer batsman_runs;
    private Integer extra_runs;
    private Integer total_runs;
    private String player_dismissed;
    private String dismissal_kind;
    private String fielder;



    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public Integer getInning() {
        return inning;
    }

    public void setInning(Integer inning) {
        this.inning = inning;
    }

    public String getBatting_team() {
        return batting_team;
    }

    public void setBatting_team(String batting_team) {
        this.batting_team = batting_team;
    }

    public String getBowling_team() {
        return bowling_team;
    }

    public void setBowling_team(String bowling_team) {
        this.bowling_team = bowling_team;
    }

    public Integer getOver() {
        return over;
    }

    public void setOver(Integer over) {
        this.over = over;
    }

    public Integer getBall() {
        return ball;
    }

    public void setBall(Integer ball) {
        this.ball = ball;
    }

    public String getBatsman() {
        return batsman;
    }

    public void setBatsman(String batsman) {
        this.batsman = batsman;
    }

    public String getNon_striker() {
        return non_striker;
    }

    public void setNon_striker(String non_striker) {
        this.non_striker = non_striker;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public Integer getIs_super_over() {
        return is_super_over;
    }

    public void setIs_super_over(Integer is_super_over) {
        this.is_super_over = is_super_over;
    }

    public Integer getWide_runs() {
        return wide_runs;
    }

    public void setWide_runs(Integer wide_runs) {
        this.wide_runs = wide_runs;
    }

    public Integer getBye_runs() {
        return bye_runs;
    }

    public void setBye_runs(Integer bye_runs) {
        this.bye_runs = bye_runs;
    }

    public Integer getLegbye_runs() {
        return legbye_runs;
    }

    public void setLegbye_runs(Integer legbye_runs) {
        this.legbye_runs = legbye_runs;
    }

    public Integer getNoball_runs() {
        return noball_runs;
    }

    public void setNoball_runs(Integer noball_runs) {
        this.noball_runs = noball_runs;
    }

    public Integer getPenalty_runs() {
        return penalty_runs;
    }

    public void setPenalty_runs(Integer penalty_runs) {
        this.penalty_runs = penalty_runs;
    }

    public Integer getBatsman_runs() {
        return batsman_runs;
    }

    public void setBatsman_runs(Integer batsman_runs) {
        this.batsman_runs = batsman_runs;
    }

    public Integer getExtra_runs() {
        return extra_runs;
    }

    public void setExtra_runs(Integer extra_runs) {
        this.extra_runs = extra_runs;
    }

    public Integer getTotal_runs() {
        return total_runs;
    }

    public void setTotal_runs(Integer total_runs) {
        this.total_runs = total_runs;
    }

    public String getPlayer_dismissed() {
        return player_dismissed;
    }

    public void setPlayer_dismissed(String player_dismissed) {
        this.player_dismissed = player_dismissed;
    }

    public String getDismissal_kind() {
        return dismissal_kind;
    }

    public void setDismissal_kind(String dismissal_kind) {
        this.dismissal_kind = dismissal_kind;
    }

    public String getFielder() {
        return fielder;
    }

    public void setFielder(String fielder) {
        this.fielder = fielder;
    }

}
