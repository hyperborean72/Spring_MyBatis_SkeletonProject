package stepanov.andrey.configuration.listener;

import java.io.Reader;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: a.stepanov
 */
@WebListener
public class CustomServletContextListener implements ServletContextListener {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public void contextInitialized(ServletContextEvent event) {

        ServletContext servletContext = event.getServletContext();
        //String resource = "mybatis-config.xml";

        try {
            //Reader reader = Resources.getResourceAsReader(resource);
            //SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            servletContext.setAttribute("sqlSessionFactory", sqlSessionFactory);
        } catch (Exception e) {
            System.out.println("FATAL ERROR: myBatis could not be initialized");
            System.exit(9);
        }
    }

    public void contextDestroyed(ServletContextEvent event) {

    }
}