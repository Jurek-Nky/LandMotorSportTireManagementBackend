# TireManagementBackend
This is the Rest-Api for our Webapplication in cooperation with Land Motorsport.

----
## Documentation
RestApi Documentation is made possible by Swagger2.
To see interactive docs just launch the api and open [this](https://localhost:8443/swagger-ui/index.html) in your browser of choice.

----
## Installation
* Create a directory named ssl and create a keystore named keystore.p12 in it. (look at https://certbot.eff.org/instructions for more help on creating a ssl certificate)
* For deploying the Docker containers you then need to create a .env file with the following contents:
  - JWT_SECRET=<CHOOSE_YOUR_JWT_SECRET>
  - JWT_EXPIRATION=<JWT_EXPIRATION_IN_MILLISECONDS>
  - KEY_STORE_PASSWORD=<THE_PASSWORD_YOU_EARLIER_CHOSE_FOR_YOUR_KEYSTORE>
  - DATABASE_USERNAME=<PICK_A_USERNAME_FOR_THE_DB>
  - DATABASE_PASSWORD=<PICK_A_PASSWORD_FOR_THE_DB>
* Now you can simply run `./compile_build` and all containers should be up and running.
