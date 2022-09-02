package com.example.news_ui.Recent_NewsRecycler;

public class Recent_helper {

    int RecentImage;
    String RecentTitle,RecentDesc;

    public Recent_helper(int recentImage, String recentTitle, String recentDesc) {
        this.RecentImage = recentImage;
        this.RecentTitle = recentTitle;
        this.RecentDesc = recentDesc;
    }

    public int getRecentImage() {
        return RecentImage;
    }

    public String getRecentTitle() {
        return RecentTitle;
    }

    public String getRecentDesc() {
        return RecentDesc;
    }
}
