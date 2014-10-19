package com.naeemquddus.foodplaces;

/**
 * Created by Naeem Quddus on 10/18/2014.
 */
public class Restaurant {
    final String name;
    final String address;
    final String phoneNumber;
    final String description;
    final String imageURL;
    final String url;
    final String rating;

    public Restaurant(String name, String address, String rating, String phoneNumber, String imageURL, String description, String url)  {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
        this.description = description;
        this.url = url;
    }

    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getRating() {return rating;}
    public String getUrl() {return url;}
    public String getImageUrl() {return imageURL;}
    public String getNumber() {return phoneNumber;}
    public String getDescription() {return description;}
}
