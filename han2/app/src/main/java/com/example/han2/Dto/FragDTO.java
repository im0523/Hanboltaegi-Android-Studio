package com.example.han2.Dto;

import java.io.Serializable;

public class FragDTO implements Serializable {

    private String no, num, title, nickname, writedate, content, id;

    public FragDTO(String no, String num, String title, String nickname, String writedate, String content, String id) {
        this.no = no;
        this.num = num;
        this.title = title;
        this.nickname = nickname;
        this.writedate = writedate;
        this.content = content;
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}