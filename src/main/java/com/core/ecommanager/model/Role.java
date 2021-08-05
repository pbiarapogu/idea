package com.core.ecommanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "[ROLE]")
public class Role implements Serializable {
    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ROLE_NAME", nullable = false, length = 64)
    private String roleName;

    @Column(name = "CREATE_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyy-MM-dd HH:mm:ss")
    private Date cDate;

    @Column(name = "MODIFIED_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyy-MM-dd HH:mm:ss")
    private Date mDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Date getcDate() {
        return cDate;
    }

    public Date getmDate() {
        return mDate;
    }
}

