package org.TelegramGPT;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ApiGPT {

    public static final String API_KEY = "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";

    public static String main(String str) throws Exception {

        var con = getHttpURLConnection(str);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append("\n");
            }

            return response.toString();
        }
    }

    private static HttpURLConnection getHttpURLConnection(String str) throws IOException, URISyntaxException {
        String url = "https://api.openai.com/v1/completions";
        URL obj = new URI(url).toURL();
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + API_KEY);


        con.setDoOutput(true);

        String requestBody = "{\"model\":\"text-davinci-003\",\"prompt\":\"" + str + "\",\"max_tokens\":150}";

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(requestBody);
            wr.flush();
        }

        return con;
    }
}
