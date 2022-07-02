package com.example.studentmark;

import androidx.annotation.NonNull;

public class Register {
    String Name;
    int Id;
    String Section;
    String Mail;
    String Password;
    int contact;

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    String ConPassword;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @NonNull

    public String getSection() {
        return Section;
    }

    public void setSection(String Section) {
        Section = Section;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConPassword() {
        return ConPassword;
    }

    public void setConPassword(String conPassword) {
        ConPassword = conPassword;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
