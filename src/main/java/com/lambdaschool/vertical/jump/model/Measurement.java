package com.lambdaschool.vertical.jump.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "measurements")
public class Measurement
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long measurementid;
    
    private double vertical;
    
    private String measurementdate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
                nullable = false)
    private User user;
    
    public Measurement()
    {
    }
    
    public Measurement(double vertical, String measurementdate, User user)
    {
        this.vertical = vertical;
        this.measurementdate = measurementdate;
        this.user = user;
    }
    
    public long getMeasurementid()
    {
        return measurementid;
    }
    
    public void setMeasurementid(long measurementid)
    {
        this.measurementid = measurementid;
    }
    
    public double getVertical()
    {
        return vertical;
    }
    
    public void setVertical(double vertical)
    {
        this.vertical = vertical;
    }
    
    public String getMeasurementdate()
    {
        return measurementdate;
    }
    
    public void setMeasurementdate(String measurementdate)
    {
        this.measurementdate = measurementdate;
    }
    
    public User getUser()
    {
        return user;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
}
