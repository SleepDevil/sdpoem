package com.example.sdpoem.logic.model;

import com.google.gson.annotations.SerializedName;

public class PoemResponse {
    private String id;
    private String author;
    private String title;
    private String content;
    private String update_time;
    private Boolean modified;
    private String type;

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public Boolean getModified() {
        return modified;
    }

    public String getType() {
        return type;
    }

    public PoemResponse(String id, String author, String title, String content, String update_time, Boolean modified, String type) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.update_time = update_time;
        this.modified = modified;
        this.type = type;
    }

    @Override
    public String toString() {
        return "PoemResponse{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", update_time='" + update_time + '\'' +
                ", modified=" + modified +
                ", type='" + type + '\'' +
                '}';
    }
}
