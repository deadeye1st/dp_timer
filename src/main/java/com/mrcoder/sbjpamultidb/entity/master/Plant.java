package com.mrcoder.sbjpamultidb.entity.master;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
@Entity
@Table(name="DP_PLANT_INFO")
public class Plant {
//    private int id;
@Transient
    private JdbcTemplate template;
    private String PROVINCE_ID;
    private String PROVINCE_NAME;
    private String CITY_ID;
    private String CITY_NAME;
    @Id
    private String PLANT_ID;
    private String PLANT_NAME;
    private Date STARTTIME_PRODUCT;
    private Double ACC_PRODUCT_AMOUNT;
    private Double ACC_PRODUCT_CAPA;
    private Double PLANT_SCALE;
    private Double YEAR_CAPACITY;
    private Double WORKER_OUT;
    private String PLANT_ADDRESS;


    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public String getPROVINCE_ID() {
        return PROVINCE_ID;
    }

    public void setPROVINCE_ID(String PROVINCE_ID) {
        this.PROVINCE_ID = PROVINCE_ID;
    }

    public String getPROVINCE_NAME() {
        return PROVINCE_NAME;
    }

    public void setPROVINCE_NAME(String PROVINCE_NAME) {
        this.PROVINCE_NAME = PROVINCE_NAME;
    }

    public String getCITY_ID() {
        return CITY_ID;
    }

    public void setCITY_ID(String CITY_ID) {
        this.CITY_ID = CITY_ID;
    }

    public String getCITY_NAME() {
        return CITY_NAME;
    }

    public void setCITY_NAME(String CITY_NAME) {
        this.CITY_NAME = CITY_NAME;
    }

    public String getPLANT_ID() {
        return PLANT_ID;
    }

    public void setPLANT_ID(String PLANT_ID) {
        this.PLANT_ID = PLANT_ID;
    }

    public String getPLANT_NAME() {
        return PLANT_NAME;
    }

    public void setPLANT_NAME(String PLANT_NAME) {
        this.PLANT_NAME = PLANT_NAME;
    }

    public Date getSTARTTIME_PRODUCT() {
        return STARTTIME_PRODUCT;
    }

    public void setSTARTTIME_PRODUCT(Date STARTTIME_PRODUCT) {
        this.STARTTIME_PRODUCT = STARTTIME_PRODUCT;
    }

    public Double getACC_PRODUCT_AMOUNT() {
        return ACC_PRODUCT_AMOUNT;
    }

    public void setACC_PRODUCT_AMOUNT(Double ACC_PRODUCT_AMOUNT) {
        this.ACC_PRODUCT_AMOUNT = ACC_PRODUCT_AMOUNT;
    }

    public Double getACC_PRODUCT_CAPA() {
        return ACC_PRODUCT_CAPA;
    }

    public void setACC_PRODUCT_CAPA(Double ACC_PRODUCT_CAPA) {
        this.ACC_PRODUCT_CAPA = ACC_PRODUCT_CAPA;
    }

    public Double getPLANT_SCALE() {
        return PLANT_SCALE;
    }

    public void setPLANT_SCALE(Double PLANT_SCALE) {
        this.PLANT_SCALE = PLANT_SCALE;
    }

    public Double getYEAR_CAPACITY() {
        return YEAR_CAPACITY;
    }

    public void setYEAR_CAPACITY(Double YEAR_CAPACITY) {
        this.YEAR_CAPACITY = YEAR_CAPACITY;
    }

    public Double getWORKER_OUT() {
        return WORKER_OUT;
    }

    public void setWORKER_OUT(Double WORKER_OUT) {
        this.WORKER_OUT = WORKER_OUT;
    }

    public String getPLANT_ADDRESS() {
        return PLANT_ADDRESS;
    }

    public void setPLANT_ADDRESS(String PLANT_ADDRESS) {
        this.PLANT_ADDRESS = PLANT_ADDRESS;
    }
}
