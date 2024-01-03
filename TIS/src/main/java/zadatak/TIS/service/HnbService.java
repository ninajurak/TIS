package zadatak.TIS.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zadatak.TIS.model.CurrencyRate;

@Service
public class HnbService {

    @Value("${hnb-api.url}")
    private String hnbApiUrl;

    private final RestTemplate restTemplate;

    public HnbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double getExchangeRate(String currencyCode) {
        String apiUrl = String.format("%s?valuta=%s", hnbApiUrl, currencyCode);
        CurrencyRate[] response = restTemplate.getForObject(apiUrl, CurrencyRate[].class);

        if (response != null && response.length > 0) {
            // Uzmi srednji tečaj kao string prvo
            String srednjiTecajString = response[0].getSrednjiTecaj();

            try {
                // Zamijeni zarez sa točkom i konvertiraj u double
                srednjiTecajString = srednjiTecajString.replace(",", ".");
                double srednjiTecaj = Double.parseDouble(srednjiTecajString);
                return srednjiTecaj;
            } catch (NumberFormatException e) {
                 e.getMessage();
            }
        }

        return null;
    }
}


