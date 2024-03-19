package co.edu.escuelaing.securespark;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.secure;
import static spark.Spark.staticFiles;

public class OperationServer {
    public static void main(String... args){
        staticFiles.location("/public");
        secure("certificados/ecikey.p12", "123456", null, null);
        port(getPort());

        get("/sin", (req,res) -> {
            double angle = Double.parseDouble(req.queryParams("angle"));
            double result = Math.sin(angle);
            System.out.println(result);
            return result;
        });

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5002;
    }

}
