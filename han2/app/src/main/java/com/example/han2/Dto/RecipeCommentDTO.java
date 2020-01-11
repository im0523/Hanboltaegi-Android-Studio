package com.example.han2.Dto;

import java.io.Serializable;

public class RecipeCommentDTO implements Serializable {
    private String nickname, rc_id, member_id, content, writedate, no;

    public RecipeCommentDTO(String nickname, String rc_id, String member_id, String content, String writedate, String no) {
        this.nickname = nickname;
        this.rc_id = rc_id;
        this.member_id = member_id;
        this.content = content;
        this.writedate = writedate;
        this.no = no;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRc_id() {
        return rc_id;
    }

    public void setRc_id(String rc_id) {
        this.rc_id = rc_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWritedate() {
        return writedate;
    }

    public void setWritedate(String writedate) {
        this.writedate = writedate;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
