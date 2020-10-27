package com.demo.ws.client.client;

import com.demo.ws.model.GetCountryRequest;
import com.demo.ws.model.GetCountryResponse;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
public class CountryWSClient {

    private final WebServiceTemplate countriesWsResource;

    public CountryWSClient(WebServiceTemplate countriesWsResource) {
        this.countriesWsResource = countriesWsResource;
    }

    public GetCountryResponse getCountryDetails(GetCountryRequest getCountryRequest) {
        return (GetCountryResponse) countriesWsResource.marshalSendAndReceive(getCountryRequest);
    }
}
