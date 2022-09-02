package com.example.news_ui.Search_recycler;

public class Search_helper {

    String SearchImageurl;
    String SearchTitle,SearchDesc,SearchDate,SearchDetailedTitle,SearchDetailedDescription;

    public String getSearchDetailedTitle() {
        return SearchDetailedTitle;
    }

    public void setSearchDetailedTitle(String searchDetailedTitle) {
        SearchDetailedTitle = searchDetailedTitle;
    }

    public String getSearchDetailedDescription() {
        return SearchDetailedDescription;
    }

    public void setSearchDetailedDescription(String searchDetailedDescription) {
        SearchDetailedDescription = searchDetailedDescription;
    }

    public String getSearchImageUrl() {
        return SearchImageurl;
    }

    public String getSearchTitle() {
        return SearchTitle;
    }

    public String getSearchDesc() {
        return SearchDesc;
    }

    public String getSearchDate() {
        return SearchDate;
    }

    public void setSearchImageUrl(String searchImage) {
        SearchImageurl = searchImage;
    }

    public void setSearchTitle(String searchTitle) {
        SearchTitle = searchTitle;
    }

    public void setSearchDesc(String searchDesc) {
        SearchDesc = searchDesc;
    }

    public void setSearchDate(String searchDate) {
        SearchDate = searchDate;
    }
}
