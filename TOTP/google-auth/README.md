# Example

1. Deploy spring boot service

2. You can send the next request:

- **Generate secret key:** http://localhost:9099/authentication/key
- **Generate QR:** http://localhost:9099/authentication/QR?key=4OYGO5S4IPWFWC3R&user=david.espitia
- **Get the current TOTP code:** http://localhost:9099/authentication/totp?key=4OYGO5S4IPWFWC3R
- **Validate current code :** http://localhost:9099/authentication?key=4OYGO5S4IPWFWC3R&code=551644

