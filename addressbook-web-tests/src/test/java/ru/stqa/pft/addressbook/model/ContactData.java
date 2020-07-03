package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private  String firstname;
    @Expose
    private  String middlename;
    @Expose
    @Column(name = "lastname")
    private  String lastname;
    @Expose
    @Type(type = "text")
    private  String address;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private  String homephone;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private  String workphone;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private  String mobilephone;
    @Expose
    @Transient
    private  String email;
    @Transient
    private  String email2;
    @Transient
    private  String email3;
    @Expose
    @Transient
    private  String group;
    @Transient
    private  String allphones;
    @Transient
    private  String allemails;
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;


    /*public void setId(int id) {
        this.id = id;
    } */

    public int getId() { return id; }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homephone;
    }

    public String getWorkPhone() {
        return workphone;
    }

    public String getMobilePhone() {
        return mobilephone;
    }

    public String getAllPhones() {
        return allphones;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() { return email2; }

    public String getEmail3() { return email3; }

    public String getAllEmails() { return allemails; }

    public String getGroup() { return group; }

    public File getPhoto() { return new File(photo); }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public ContactData withWorkPhone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContactData withMobilePhone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public ContactData withAllPhones(String allphones) {
        this.allphones = allphones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allemails) {
        this.allemails = allemails;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}
