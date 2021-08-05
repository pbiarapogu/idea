package com.core.ecommanager.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="[user]")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private long userId;
    @Column(name="USERNAME", nullable = false, length = 64)
    private String userName;

    @Column(name="PASSWORD", nullable = false, length = 64)
    private String up;

    @Column(name="CREATE_DATE")
    private String createDate;

    @Column(name="MODIFIED_DATE")
    private String modifiedDate;

    @Column(name="PHONE_NUMBER")
    private String phnNumber;

    @Column(name="LOCATION")
    private String location;

    @Column(name="IS_AES_ENCRYPT")
    private int isAesEncrypted;

    @Column(name="ACCOUNT_LOCKED")
    private int isLocked;

    @Column(name="LAST_LOGIN_DATE")
    private String lastLoginDate;

    @Column(name="LOGIN_ATTEMPTS")
    private int loginAttempts;

    @Column(name="PASSWORD_EXPIRATION_DATE")
    private String passExpDate;

    @Column(name="IS_ACTIVE")
    private int isActive;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "USER_FACILITY_XREF",joinColumns = {@JoinColumn(name="USER_ID") },inverseJoinColumns = {@JoinColumn(name="FACILITY_ID")})
//    private Set<Facility> facilities;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE_XREF", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private List<Role> userRoles;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getPhnNumber() {
        return phnNumber;
    }

    public void setPhnNumber(String phnNumber) {
        this.phnNumber = phnNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getIsAesEncrypted() {
        return isAesEncrypted;
    }

    public void setIsAesEncrypted(int isAesEncrypted) {
        this.isAesEncrypted = isAesEncrypted;
    }

    public int getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(int isLocked) {
        this.isLocked = isLocked;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public String getPassExpDate() {
        return passExpDate;
    }

    public void setPassExpDate(String passExpDate) {
        this.passExpDate = passExpDate;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }
}
