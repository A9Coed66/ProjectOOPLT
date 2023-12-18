package main.getdata;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CollectionColectData {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.opensea.io/api/v2/collections/the-ant-republic/stats"))
                .header("accept", "application/json")
                .header("x-api-key", "600aaa1838a84f7ba59f5d6c277e5e93")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println(response.body());
    }

}
