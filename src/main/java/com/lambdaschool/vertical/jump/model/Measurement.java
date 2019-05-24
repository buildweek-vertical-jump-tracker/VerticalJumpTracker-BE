package com.lambdaschool.vertical.jump.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.bytebuddy.build.Plugin;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "measurements")
public class Measurement extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long measurementid;
    
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    private double vertical;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties("measurements")
    private User user;
    
    public Measurement()
    {
    }
    
    public Measurement(double vertical, User user)
    {
        this.vertical = vertical;
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
    
    public User getUser()
    {
        return user;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
    
    public String getCreationDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(creationDate);
    }
    
    public Date getRawDate()
    {
        return creationDate;
    }
}
