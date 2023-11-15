import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class ChuckNorrisJokeFetcher {

    public static void main(String[] args) {
        try {
            // URL del endpoint del API
            String apiUrl = "https://api.chucknorris.io/jokes/random";

            // Realizar la solicitud HTTP GET
            String jsonResponse = sendGetRequest(apiUrl);

            // Parsear la respuesta JSON y almacenarla en un HashMap
            HashMap<String, String> chuckNorrisJoke = parseJsonResponse(jsonResponse);

            // Imprimir el chiste de Chuck Norris y su categoría
            System.out.println("Chiste de Chuck Norris: " + chuckNorrisJoke.get("joke"));
            System.out.println("Categoría: " + chuckNorrisJoke.get("category"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sendGetRequest(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Configurar la solicitud
        connection.setRequestMethod("GET");

        // Obtener la respuesta
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        // Cerrar la conexión y el lector
        reader.close();
        connection.disconnect();

        return response.toString();
    }

    private static HashMap<String, String> parseJsonResponse(String jsonResponse) {
        // Aquí puedes usar una biblioteca JSON como Jackson o simplemente parsear
        // manualmente el JSON
        // En este ejemplo, se asume que la respuesta es un JSON simple con campos
        // 'value' y 'category'
        HashMap<String, String> chuckNorrisJoke = new HashMap<>();
        chuckNorrisJoke.put("joke", jsonResponse);
        chuckNorrisJoke.put("category", "Chuck Norris"); // Puedes modificar esto según la estructura real del JSON

        return chuckNorrisJoke;
    }
}