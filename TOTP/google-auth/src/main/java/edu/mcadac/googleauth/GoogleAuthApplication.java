package edu.mcadac.googleauth;

import edu.mcadac.googleauth.authenticator.GoogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  Camilo Espitia - dcespitiam@gmail.com
 */
@SpringBootApplication
public class GoogleAuthApplication {


    @Autowired
    private GoogleService googleService;

    //@EventListener(ApplicationReadyEvent.class)
    public void test(){

        System.out.println("========== Start test! ==========");
        //googleService.generateQR(googleService.createKey());

    }

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(GoogleAuthApplication.class, args);
    }

}

