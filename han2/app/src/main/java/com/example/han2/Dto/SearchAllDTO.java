package com.example.han2.Dto;

import java.io.Serializable;

public class SearchAllDTO implements Serializable {

    private String no, content, count, writedate;

    public SearchAllDTO(String no, String content, String count, String writedate) {
        this.no = no;
        this.content = content;
        this.count = count;
        this.writedate = writedate;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getWritedate() {
        return writedate;
    }

    public void setWritedate(String writedate) {
        this.writedate = writedate;
    }
}
