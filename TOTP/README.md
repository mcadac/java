# Google Authenticator

1. The time-based one-time password algorithm RFC 6238
2. The QR code can be obtained using a generated URL, which relies on the Google
Chart HTTP API to create a compliant QR code encoding the necessary information.

## The two basic operations

1. **Credential creation:** 
    It is the process in which the server generates the shared secret and share it with the client. 
    So that he can store it and configurate its token, it is sending encoded into a QR code.
    
2. **Credential authentication:** 
    It is the process in which the server applies the TOTP algorithm to the shared secret and the token generated password 
    of the prover to dertermine its authenticity. 
    
### References

https://github.com/wstrange/GoogleAuth

