package com.example.news_ui.Saved_recycler;

public class Saved_helperClass {

    String ImageUrl;
    String SavedText,SavedDate,Saveddesc;



    public String getImagesSaved() {
        return ImageUrl;
    }

    public String getSavedText() {
        return SavedText;
    }
    public String getSavedDate(){
        return SavedDate;
    }
    public String getSaveddesc(){
        return Saveddesc;
    }

    public void setImagesSaved(String imagesSaved) {
        ImageUrl = imagesSaved;
    }

    public void setSavedText(String savedText) {
        SavedText = savedText;
    }
    public void setSavedDate(String Saveddate){
        SavedDate = Saveddate;
    }
    public void setSaveddesc(String SavedDesc){
        Saveddesc = SavedDesc;
    }
}
