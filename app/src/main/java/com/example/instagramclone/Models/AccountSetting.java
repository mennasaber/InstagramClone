package com.example.instagramclone.Models;

public class AccountSetting {
    private String name;
    private String website;
    private String bio;
    private String photo;
    private int followers ;
    private int following;
    private int posts;

    public AccountSetting(String name, String website, String bio, String photo, int followers, int following, int posts) {
        this.name = name;
        this.website = website;
        this.bio = bio;
        this.photo = photo;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }
}
