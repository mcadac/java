package edu.mcadac.googleauth.authenticator;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Component
public class GoogleService {

    private static final String APP_NAME =  "PayU-Admin";

    private final GoogleAuthenticator googleAuthenticator;

    /**
     *
     */
    public GoogleService(){
        this.googleAuthenticator = new GoogleAuthenticator();
    }


    /**
     *
     */
    public GoogleAuthenticatorKey createKey(){

        final GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
        System.out.println("Key : " + key.getKey() + "Code : " + key.getVerificationCode());
        return key;

    }

    /**
     *
     */
    public String generateQR(final String key){

        Objects.requireNonNull(key,"The Authenticator key cannot be null!");

        final GoogleAuthenticatorKey googleAuthenticatorKey = new GoogleAuthenticatorKey.Builder(key).build();

        final String otpAuthURL =
        GoogleAuthenticatorQRGenerator.getOtpAuthURL(APP_NAME,"david.espitia@payultama.com", googleAuthenticatorKey);

        System.out.println("Url : " + otpAuthURL);
        return otpAuthURL;

    }


    /**
     *
     * @param key
     * @return
     */
    public int getPassword(final String key){

       final int password = googleAuthenticator.getTotpPassword(key);
       System.out.println("OTP password : " + password);
       return password;

    }


    /**
     *
     * @param key
     * @param code
     * @return
     */
    public boolean validateTotp(final String key, final int code){

       return googleAuthenticator.authorize(key,code);

    }


}
