package com.lambdaschool.vertical.jump.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "goals")
public class Goal
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long goalid;
    
    private double goalvertical;
    private boolean completed;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("goals")
    private User user;
    
    public Goal()
    {
    }
    
    public Goal(double goalvertical, User user)
    {
        this.goalvertical = goalvertical;
        this.user = user;
        this.completed = false;
    }
    
    public long getGoalid()
    {
        return goalid;
    }
    
    public void setGoalid(long goalid)
    {
        this.goalid = goalid;
    }
    
    public double getGoalvertical()
    {
        return goalvertical;
    }
    
    public void setGoalvertical(double goalvertical)
    {
        this.goalvertical = goalvertical;
    }
    
    public boolean isCompleted()
    {
        return completed;
    }
    
    public void setCompleted(boolean completed)
    {
        this.completed = completed;
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
