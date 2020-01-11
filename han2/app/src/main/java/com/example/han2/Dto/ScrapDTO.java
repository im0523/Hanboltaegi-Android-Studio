package com.example.han2.Dto;

import java.io.Serializable;

public class ScrapDTO implements Serializable {

    private int screcipe_id;
    private String scr_userid, scr_recipeid, target_userid, scr_date;

    public ScrapDTO(int screcipe_id, String scr_userid, String scr_recipeid, String target_userid, String scr_date) {
        this.screcipe_id = screcipe_id;
        this.scr_userid = scr_userid;
        this.scr_recipeid = scr_recipeid;
        this.target_userid = target_userid;
        this.scr_date = scr_date;
    }


    public int getScrecipe_id() {
        return screcipe_id;
    }

    public void setScrecipe_id(int screcipe_id) {
        this.screcipe_id = screcipe_id;
    }

    public String getScr_userid() {
        return scr_userid;
    }

    public void setScr_userid(String scr_userid) {
        this.scr_userid = scr_userid;
    }

    public String getScr_recipeid() {
        return scr_recipeid;
    }

    public void setScr_recipeid(String scr_recipeid) {
        this.scr_recipeid = scr_recipeid;
    }

    public String getTarget_userid() {
        return target_userid;
    }

    public void setTarget_userid(String target_userid) {
        this.target_userid = target_userid;
    }

    public String getScr_date() {
        return scr_date;
    }

    public void setScr_date(String scr_date) {
        this.scr_date = scr_date;
    }
}
