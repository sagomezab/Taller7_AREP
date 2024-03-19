package co.edu.escuelaing.securespark;

import static spark.Spark.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class HelloWorld {
    private static SHA256 encrypting = new SHA256();
    private static Map<String, String> userCredentials = new HashMap<>();

    public static void main(String[] args) throws NoSuchAlgorithmException {

        addUserCredentials("sagomezab", "password123");
        addUserCredentials("mapgomezab", "abc123");
        addUserCredentials("javierfgomezb", "contraseñaSegura");

        staticFiles.location("/public");
        secure("certificados/ecikeystore.p12", "123456", null, null);
        port(getPort());

        get("/login", (req, res) -> {
            String user = req.queryParams("user");
            String psswd = req.queryParams("password");
            if (authenticate(user, psswd)) {
                System.out.println("Si entro");
                return "Inicio de sesion exitoso para el usuario";
            } else {
                System.out.println("No entro");
                return "Credenciales invalidas";
            }
        });
    }

    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001; 
    }

    private static void addUserCredentials(String user, String password) throws NoSuchAlgorithmException {
        String hashedPassword = encrypting.emcrypt(password);
        userCredentials.put(user, hashedPassword);
    }

    private static boolean authenticate(String user, String password) throws NoSuchAlgorithmException {
        String storedPassword = userCredentials.get(user);
        if (storedPassword != null) {
            String hashedPassword = encrypting.emcrypt(password);
            return hashedPassword.equals(storedPassword);
        }
        return false;
    }

} 