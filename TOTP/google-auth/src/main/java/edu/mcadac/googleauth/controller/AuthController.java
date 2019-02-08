package edu.mcadac.googleauth.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;
import edu.mcadac.googleauth.authenticator.AuthenticatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Access point to service
 *
 * Camilo Espitia - dcespitiam@gmail.com
 */
@RestController
@RequestMapping("authentication")
public class AuthController {

    /** Controller's logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    /**
     * Authenticator service used to process the request message
     */
    private AuthenticatorService authenticatorService;


    /**
     * Constructor with {@link Autowired}
     * @param authenticatorService
     */
    @Autowired
    public AuthController(final AuthenticatorService authenticatorService){

        this.authenticatorService = authenticatorService;

    }


    /**
     * Generates a new secret key to be used in order to create a QR
     *
     * @return secret key to create a QR
     */
    @GetMapping("/key")
    public String generateKey(){
        return authenticatorService.createKey().getKey();
    }

    /**
     * Generate a QR with the key and user sent
     *
     * @param key secret key
     * @param user user associated to the secret key
     * @return QR's url
     */
    @GetMapping("/QR")
    public String generateQR(@RequestParam final String key, @RequestParam final String user) {

        LOGGER.info("Key sent: {} and user: {}", key, user);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 256;-
        int height = 256;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // create an empty image
        int white = 255 << 16 | 255 << 8 | 255;
        int black = 0;

        try{

            final BitMatrix bitMatrix = qrCodeWriter
                    .encode(authenticatorService.generateQRTotp(key, user), BarcodeFormat.QR_CODE, width, height);

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    image.setRGB(i, j, bitMatrix.get(i, j) ? black : white);
                }
            }

            try {
                ImageIO.write(image, "jpg", new File("QR.jpg")); // save QR image to disk
            } catch (IOException e) {

                e.printStackTrace();
            }


        } catch(Exception e){
            e.printStackTrace();
        }


        return authenticatorService.generateQR(key, user);
    }

    /**
     * Returns the current TOTP code of the secret key sent
     *
     * @param key secret key
     * @return The current TOTP code
     */
    @GetMapping("/totp")
    public int getTotp(@RequestParam final String key){

        LOGGER.info("Key sent to generate TOTP: {}", key);
        return authenticatorService.getPassword(key);

    }

    /**
     * Validate the code with the secret key sent
     *
     * @param key secret key
     * @param code code to validate
     * @return true if code is valid
     */
    @GetMapping
    public boolean validate(@RequestParam final String key, @RequestParam final int code){

        LOGGER.info("Validate key: {} with code: {}", key, code);
        return authenticatorService.validateTotp(key, code);
    }


}
