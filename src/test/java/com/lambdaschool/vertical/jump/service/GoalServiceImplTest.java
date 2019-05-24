package com.lambdaschool.vertical.jump.service;

import com.lambdaschool.vertical.jump.JumpApplicationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JumpApplicationTest.class)
class GoalServiceImplTest
{
    @Autowired
    private GoalService goalService;
    
    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }
    
    @AfterEach
    public void tearDown()
    {
    }
    
    @Test
    public void save()
    {
    }
    
    @Test
    public void updateGoal()
    {
    }
    
    @Test
    public void deleteGoal()
    {
    }
    
    @Test
    public void getGoalsByUserId()
    {
    }
}