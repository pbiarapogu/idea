package com.core.ecommanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

@Entity
@Table(name="facility")
public class Facility {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="FACILITY_ID")
    private long facilityId;

    @Column(name="FACILITY_TYPE_CODE", nullable = false, length = 20)
    private String facilityTypeCode;

    @Column(name="FACILITY_NAME", nullable = false, length = 40)
    private String facilityName;

    @Column(name="BANNER_ID", nullable = false)
    private int bannerId;

    @Column(name="ADDRESS1", length = 40)
    private String address1;

    @Column(name="ADDRESS2", length = 40)
    private String address2;

    @Column(name="CITY", length = 30)
    private String city;

    @Column(name="STATE", length = 2)
    private String state;

    @Column(name="ZIP_CODE", length = 10)
    private String zipCode;

    @Column(name="LATITUDE")
    private double latitude;

    @Column(name="LONGITUDE")
    private double longitude;

    @Column(name="DELIVERY_TYPE_CODE", length = 20)
    private String deliveryTypeCode;

    @Column(name="PARENT_FACILITY_ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer parentFacilityId;

    @Column(name="PHONE_NUMBER", length = 16)
    private String phoneNumber;

    @Column(name="EXTERNAL_ID", length = 10)
    private String externalId;

    @Column(name="STATUS", length = 20)
    private String status;

    @Column(name="FAX_NUMBER", length = 16)
    private String faxNumber;

    @Column(name="PHARMACY_FAX", length = 16)
    private String pharmacyFax;

    @Column(name="PHARMACY_PHONE", length = 16)
    private String pharmacyPhone;

    @Column(name="IS_LEGACY_STORE", nullable = false)
    private int isLegacyStore;

    @Column(name="DELIVERY_BY", nullable = false, length = 20)
    private String deliveryBy;

    public long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(long facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityTypeCode() {
        return facilityTypeCode;
    }

    public void setFacilityTypeCode(String facilityTypeCode) {
        this.facilityTypeCode = facilityTypeCode;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public int getBannerId() {
        return bannerId;
    }

    public void setBannerId(int bannerId) {
        this.bannerId = bannerId;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDeliveryTypeCode() {
        return deliveryTypeCode;
    }

    public void setDeliveryTypeCode(String deliveryTypeCode) {
        this.deliveryTypeCode = deliveryTypeCode;
    }

    public Integer getParentFacilityId() {
        return parentFacilityId;
    }

    public void setParentFacilityId(Integer parentFacilityId) {
        this.parentFacilityId = parentFacilityId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getPharmacyFax() {
        return pharmacyFax;
    }

    public void setPharmacyFax(String pharmacyFax) {
        this.pharmacyFax = pharmacyFax;
    }

    public String getPharmacyPhone() {
        return pharmacyPhone;
    }

    public void setPharmacyPhone(String pharmacyPhone) {
        this.pharmacyPhone = pharmacyPhone;
    }

    public int getIsLegacyStore() {
        return isLegacyStore;
    }

    public void setIsLegacyStore(int isLegacyStore) {
        this.isLegacyStore = isLegacyStore;
    }

    public String getDeliveryBy() {
        return deliveryBy;
    }

    public void setDeliveryBy(String deliveryBy) {
        this.deliveryBy = deliveryBy;
    }
}
