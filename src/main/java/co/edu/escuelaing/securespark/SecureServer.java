package co.edu.escuelaing.securespark;

import static spark.Spark.*;

public class SecureServer {
    public static void main( String[] args )
    {
        staticFileLocation("/public");
        secure("certificados/ecikeyserver.p12", "123456", null, null);
        port(getPort());

        get("/login", (req, res) -> {
            return URLReader.read("https://localhost:5001/login?user=" + req.queryParams("user") + "&password=" + req.queryParams("password"), "myTrustStore.p12");
        });

        get("/sin", (req, res) -> {
            return URLReader.read("https://localhost:5002/sin?angle=" + req.queryParams("angle"), "myTrustStore.p12");
        });

    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
}
