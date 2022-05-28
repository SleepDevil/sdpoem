package com.example.sdpoem.ui.shicidb.placeholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceholderContent {

    public static class PlaceholderItem {
        public final String author;
        public final String title;
        public final String rhythmic;
        public final List<String> paragraphs;
        public final List<String> tags;

        public PlaceholderItem(String author, String title, String rhythmic, List<String> paragraphs, List<String> tags) {
            this.author = author;
            this.title = title;
            this.paragraphs = paragraphs;
            this.rhythmic = rhythmic;
            this.tags = tags;
        }

        @Override
        public String toString() {
            return "jusaaaaaaa";
        }
    }
}