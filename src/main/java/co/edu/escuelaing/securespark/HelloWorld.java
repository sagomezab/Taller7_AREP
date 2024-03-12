package co.edu.escuelaing.securespark;

import static spark.Spark.*;

public class HelloWorld {
    private SHA256 encrypting;

    public static void main(String[] args) {

        secure("certificados/ecikeystore.p12", "123456", null, null);
        port(getPort());
        get("/login", (req, res) -> {
            double user = Double.parseDouble(req.queryParams("user"));
            double psswd = Double.parseDouble(req.queryParams("password"));
            return user;
        });
    }

    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; 
    } 

} 