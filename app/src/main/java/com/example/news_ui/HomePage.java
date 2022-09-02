package com.example.news_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomePage extends AppCompatActivity {


    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        chipNavigationBar = findViewById(R.id.ChipNavigationBar);
        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_Container,new Home_Fragment()).commit();
        chipNavigationBar.setItemSelected(R.id.bottom_navDiscover,true);
        bottomMenu();

    }

    private void bottomMenu() {

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){
                    case R.id.bottom_navDiscover:
                        fragment = new Home_Fragment();
                        break;
                    case R.id.bottom_navCategories:
                        fragment = new Categories_Fragment();
                        break;
                    case R.id.bottom_navSearch:
                        fragment = new Search_Fragment();
                        break;
                    case R.id.bottom_navSave:
                        fragment = new Save_Fragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_Container,fragment).commit();
            }
        });
    }
}