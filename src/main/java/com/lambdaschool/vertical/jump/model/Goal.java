package com.lambdaschool.vertical.jump.model;

import javax.persistence.*;

@Entity
@Table(name = "goals")
public class Goal
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long goalid;
    
    
}
