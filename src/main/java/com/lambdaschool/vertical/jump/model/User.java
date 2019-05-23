package com.lambdaschool.vertical.jump.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// User is considered the parent entity of all - the Grand Poobah!

@Entity
@Table(name = "users")
public class User extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false,
            unique = true)
    private String username;
    
    private String height;
    private int exercisescompleted = 0;
    private int startpoint; // how many exercises completed when workout plan is added
    private int endpoint; // number of exercises needed to finish (startpoint + plan length)
    private int interval; // number of days between reminders AFTER plan is completed (exercisescompleted >= endpoint)
    private boolean needmeasurement;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user")
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JsonIgnoreProperties("user")
    private List<UserRoles> userRoles = new ArrayList<>();
    
    @OneToMany(mappedBy = "user",
                orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JsonIgnoreProperties("user")
    private List<Measurement> measurements = new ArrayList<>();
    
    @OneToMany(mappedBy = "user",
                orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JsonIgnoreProperties("user")
    private List<Goal> goals = new ArrayList<>();

    public User()
    {
    }

    public User(String username, String password, List<UserRoles> userRoles)
    {
        setUsername(username);
        setPassword(password);
        for (UserRoles ur : userRoles)
        {
            ur.setUser(this);
        }
        this.userRoles = userRoles;
    }
    
    public User(String username, String height, String password, List<UserRoles> userRoles, List<Measurement> measurements)
    {
        setUsername(username);
        setPassword(password);
        for (UserRoles ur : userRoles)
        {
            ur.setUser(this);
        }
        this.userRoles = userRoles;
        this.height = height;
        this.exercisescompleted = 0;
        this.measurements = measurements;
        startpoint = 0;
        endpoint = 0;
        interval = 0;
        needmeasurement = false;
    }
    
    public String getHeight()
    {
        return height;
    }
    
    public void setHeight(String height)
    {
        this.height = height;
    }
    
    public int getExercisescompleted()
    {
        return exercisescompleted;
    }
    
    public void setExercisescompleted(int exercisescompleted)
    {
        this.exercisescompleted = exercisescompleted;
    }
    
    public List<Measurement> getMeasurements()
    {
        return measurements;
    }
    
    public void setMeasurements(List<Measurement> measurements)
    {
        this.measurements = measurements;
    }
    
    public long getUserid()
    {
        return userid;
    }
    
    public void setUserid(long userid)
    {
        this.userid = userid;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public List<UserRoles> getUserRoles()
    {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles)
    {
        this.userRoles = userRoles;
    }

    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userRoles)
        {
            String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }
        return rtnList;
    }
    
    public List<Goal> getGoals()
    {
        return goals;
    }
    
    public void setGoals(List<Goal> goals)
    {
        this.goals = goals;
    }
}