package edu.mcadac.googleauth.controller;

import edu.mcadac.googleauth.authenticator.GoogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authentication")
public class AuthController {

    private GoogleService googleService;


    /**
     *
     * @param googleService
     */
    @Autowired
    public AuthController(final GoogleService googleService){

        this.googleService = googleService;

    }


    /**
     *
     * @return
     */
    @GetMapping("/key")
    public String generateKey(){

        return googleService.createKey().getKey();

    }

    /**
     *
     * @return
     */
    @GetMapping("/QR")
    public String generateQR(@RequestParam final String key){

        System.out.println("Key sent: " + key);
        return googleService.generateQR(key);
    }

    /**
     *
     * @return
     */
    @GetMapping("/totp")
    public int getTotp(@RequestParam final String key){

        System.out.println("Key sent: " + key);
        return googleService.getPassword(key);

    }


}
