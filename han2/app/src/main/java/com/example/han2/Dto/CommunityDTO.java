package com.example.han2.Dto;

import java.io.Serializable;

public class CommunityDTO implements Serializable {

    String no, content, nickname, writedate, co_file1, co_filename1, userid;

    public CommunityDTO(String no, String content, String nickname, String writedate, String co_file1, String co_filename1, String userid) {
        this.no = no;
        this.content = content;
        this.nickname = nickname;
        this.writedate = writedate;
        this.co_file1 = co_file1;
        this.co_filename1 = co_filename1;
        this.userid = userid;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWritedate() {
        return writedate;
    }

    public void setWritedate(String writedate) {
        this.writedate = writedate;
    }

    public String getCo_file1() {
        return co_file1;
    }

    public void setCo_file1(String co_file1) {
        this.co_file1 = co_file1;
    }

    public String getCo_filename1() {
        return co_filename1;
    }

    public void setCo_filename1(String co_filename1) {
        this.co_filename1 = co_filename1;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}


