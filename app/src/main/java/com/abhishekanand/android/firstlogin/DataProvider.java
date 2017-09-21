package com.abhishekanand.android.firstlogin;

/**
 * Created by Jerry on 10/30/2015.
 */
public class DataProvider {
    private String name;
    private String mob;
    private String email;
    private String id;

    public DataProvider(String name,String mob,String email, String id){
        this.name=name;
        this.mob=mob;
        this.email=email;
        this.id=id;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {  this.mob = mob;  }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {    this.email = email;  }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
