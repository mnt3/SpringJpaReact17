package lt.akademija.jpaexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestData {

    public Object getNewEntity1() {
        /*
         * Replace this code with construction code for your entity
         */
        Object yourObject = new Object();
        return yourObject;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestData.class, args);
    }
}
