package com.example.project_a.otherClass;

public class papers {
    public String title,content,date ;

    public papers(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}
