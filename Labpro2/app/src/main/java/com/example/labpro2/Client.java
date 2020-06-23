package com.example.labpro2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "client_table")
public class Client {
    @PrimaryKey(autoGenerate = true)
    private int cid;
    private String ccode;
    private String cname;
    private String emailaddress;
    private String physicaladdress;
    //private int mobile;
    //constructor

    public Client(String ccode, String cname, String emailaddress, String physicaladdress) {
        this.ccode = ccode;
        this.cname = cname;
        this.emailaddress = emailaddress;
        this.physicaladdress = physicaladdress;
    }
    // setter methods

    public void setCid(int cid) {
        this.cid = cid;
    }


    //getter methods


    public int getCid() {
        return cid;
    }

    public String getCcode() {
        return ccode;
    }

    public String getCname() {
        return cname;
    }


    public String getEmailaddress() {
        return emailaddress;
    }

    public String getPhysicaladdress() {
        return physicaladdress;
    }
}
