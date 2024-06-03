package ConversorDaApi;


import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ConversorApi {
    private String base_code;
    private String target_code;
    private String convertion_rate;
    private double amount;
    private String apiKey;

    List<Conversor> listaConversor = new ArrayList<>();

    public ConversorApi(Conversor conversor){
        this.base_code = conversor.getBase_code();
        this.target_code = conversor.getTarget_code();
        this.amount = conversor.getValor();
    }
    public String chamandoApi() throws RuntimeException {
        this.apiKey = "d5c7134cd6348a34e6d4137e";
        String uriConsulta = this.apiKey + "/pair/" + this.base_code + "/" + this.target_code + "/" + this.amount;

        URI uri = URI.create("https://v6.exchangerate-api.com/v6/" + uriConsulta);


        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = (String)response.body();
            Gson gson = new Gson();
            Conversor converter = (Conversor)gson.fromJson(json, Conversor.class);
            return converter.toString();
        } catch (Exception var9) {
            throw new RuntimeException("Algo deu errado!");
        }

    }

    public List<Conversor> getListConversor(){
        return listaConversor;
    }

}