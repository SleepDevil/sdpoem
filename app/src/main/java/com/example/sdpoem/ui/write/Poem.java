package com.example.sdpoem.ui.write;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Poem {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    public String Title;

    @ColumnInfo(name = "content")
    public String Content;

    @ColumnInfo(name = "writtentime")
    public String WrittenTime;

    public Poem(String Title, String Content, String WrittenTime) {
        this.Title = Title;
        this.Content = Content;
        this.WrittenTime = WrittenTime;
    }
}
