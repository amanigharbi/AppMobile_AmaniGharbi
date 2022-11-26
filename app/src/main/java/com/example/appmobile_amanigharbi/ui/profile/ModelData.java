package com.example.appmobile_amanigharbi.ui.profile;

public class ModelData {
    private int id;
    private String Gender;
    private String FirstName;
    private String LastName;
    private String Age;

    public ModelData(int id, String Gender, String FirstName, String LastName,String Age) {
        this.id = id;
        this.Gender = Gender;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Age = Age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    @Override
    public String toString() {
        return "ModelData{" +
                "id=" + id +
                ", Gender='" + Gender + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Age='" + Age + '\'' +
                '}';
    }
}
