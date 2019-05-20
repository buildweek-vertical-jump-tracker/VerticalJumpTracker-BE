package com.lambdaschool.vertical.jump.model;

import javax.persistence.*;

@Entity
@Table(name = "measurements")
public class Measurement
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
}
