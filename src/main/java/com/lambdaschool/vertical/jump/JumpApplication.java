package com.lambdaschool.vertical.jump;

import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.context.ApplicationContext;
        import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
        import org.springframework.web.servlet.DispatcherServlet;
        import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableJpaAuditing
@SpringBootApplication
public class JumpApplication
{
    
    public static void main(String[] args)
    {
        ApplicationContext ctx = SpringApplication.run(JumpApplication.class, args);
        
        DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }
    
}
