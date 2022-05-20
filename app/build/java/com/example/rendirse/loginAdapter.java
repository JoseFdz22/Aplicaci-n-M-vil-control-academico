package com.example.rendirse;

import android.content.Context;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;


public class loginAdapter extends FragmentPagerAdapter{
    private Context context;
    int totalTabs;


    public loginAdapter( FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context= context;
        this.totalTabs= totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }


    public Fragment getItem(int position){
        switch (position){
            case 0:
                LOginTabFragment lOginTabFragment = new LOginTabFragment();

                return lOginTabFragment;
            case 1:
                SignupTabFragment signupTabFragment =new SignupTabFragment();
                return  signupTabFragment;

            default:
                return null;
        }
    }


}
