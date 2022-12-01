import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String title = "inception";
        String url = "https://imdb8.p.rapidapi.com/auto-complete?q=" + title;
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-RapidAPI-Key", "SECRET_KEY")
                .header("X-RapidAPI-Host", "imdb8.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(response.body());
        String Json = response.body();
        incomingJson cur = objectMapper.readValue(Json, incomingJson.class);

        System.out.println("Name " + cur.getD()[0].l);
        System.out.println("Genre " + cur.getD()[0].q);
        System.out.println("Cast " + cur.getD()[0].s);
    }
}
