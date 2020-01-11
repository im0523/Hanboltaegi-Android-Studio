package com.example.han2.Dto;

public class MemberDTO {
    String id, pw, nickname, email, cnt;
    String grade_id, user_pic, follower, following;

    public MemberDTO(String id, String pw, String nickname, String email, String cnt, String grade_id, String user_pic, String follower, String following) {
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.email = email;
        this.cnt = cnt;
        this.grade_id = grade_id;
        this.user_pic = user_pic;
        this.follower = follower;
        this.following = following;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }
}
