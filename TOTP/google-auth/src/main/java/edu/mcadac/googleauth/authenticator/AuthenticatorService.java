package edu.mcadac.googleauth.authenticator;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Authenticator
 *
 * Camilo Espitia - dcespitiam@gmail.com
 */
@Service
public class AuthenticatorService {

    /** Controller's logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatorService.class);

    /** Application name that be used with Two factor auth */
    private static final String APP_NAME =  "mcadac-test";

    /**
     * Google authenticator
     */
    private final GoogleAuthenticator googleAuthenticator;

    /**
     * Constructor
     */
    public AuthenticatorService(){
        this.googleAuthenticator = new GoogleAuthenticator();
    }


    /**
     * Create a new secret key
     */
    public GoogleAuthenticatorKey createKey(){

        final GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
        LOGGER.info("Key : {} code: {} ",key.getKey(),key.getVerificationCode());
        return key;

    }

    /**
     * Build the QR with the key and user sent
     *
     * @param key
     * @param user
     * @return QR's URL
     */
    public String generateQR(final String key, final String user){

        Objects.requireNonNull(key,"The Authenticator key cannot be null!");
        final GoogleAuthenticatorKey googleAuthenticatorKey = new GoogleAuthenticatorKey.Builder(key).build();
        final String otpAuthURL = GoogleAuthenticatorQRGenerator.getOtpAuthURL(APP_NAME,user, googleAuthenticatorKey);
        final String qr =  GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(APP_NAME,user, googleAuthenticatorKey);

        LOGGER.info("Url : {}",otpAuthURL);
        return otpAuthURL;

    }

    /**
     * Build the QR with the key and user sent
     *
     * @param key
     * @param user
     * @return QR's URL
     */
    public String generateQRTotp(final String key, final String user){

        Objects.requireNonNull(key,"The Authenticator key cannot be null!");
        final GoogleAuthenticatorKey googleAuthenticatorKey = new GoogleAuthenticatorKey.Builder(key).build();
        final String totp =  GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(APP_NAME,user, googleAuthenticatorKey);

        LOGGER.info("totp : {}",totp);
        return totp;

    }



    /**
     * Gets the current TOTP code
     *
     * @param key
     * @return the current code associated to the specific secret key
     */
    public int getPassword(final String key){

       final int password = googleAuthenticator.getTotpPassword(key);
       LOGGER.info("OTP password : {}",password);
       return password;

    }


    /**
     * Validate code
     *
     * @param key
     * @param code
     * @return true if the TOTP code is valid
     */
    public boolean validateTotp(final String key, final int code){

       return googleAuthenticator.authorize(key,code);

    }


}
