package com.example.myapplication;

import java.util.Objects;

public class DetailModel {
    private String mName;
    private String mLogin;
    private String mLocation;
    private int mContributions;
    private int mFollowers;
    private String mOrganizations;
    private int mBool;

    public DetailModel(String name,String login,String location,int contributions,int followers,String organizations, int bool) {
        mName = name;
        mLogin = login;
        mLocation = location;
        mContributions = contributions;
        mFollowers = followers;
        mOrganizations = organizations;
        mBool = bool;
    }

    public String getName() {
        return mName;
    }

    public String getLogin() {
        return mLogin;
    }

    public String getLocation() {
        return mLocation;
    }

    public int getContributions(){
        return mContributions;
    }

    public int getFollowers() {
        return mFollowers;
    }

    public String getOrganizations(){return mOrganizations;};

    public int getImageRes() {
        return mBool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetailModel)) return false;
        DetailModel that = (DetailModel) o;
        return mFollowers == that.mFollowers &&
                mBool == that.mBool &&
                mName.equals(that.mName) &&
                mContributions == that.mContributions &&
                mLocation.equals(that.mLocation) &&
                mLogin.equals(that.mLogin) &&
                mOrganizations.equals(that.mOrganizations);
    }
    @Override
    public int hashCode() {
        return Objects.hash(mName,mLogin,mLocation,mContributions,mFollowers,mOrganizations,mBool);
    }
}
