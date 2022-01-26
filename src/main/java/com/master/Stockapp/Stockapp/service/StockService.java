package com.master.Stockapp.Stockapp.service;

import lombok.AllArgsConstructor;
import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.assets.Asset;
import net.jacobpeterson.alpaca.model.endpoint.assets.enums.AssetClass;
import net.jacobpeterson.alpaca.model.endpoint.assets.enums.AssetStatus;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class StockService {

    private final AlpacaAPI alpacaAPI = new AlpacaAPI();

    public List<Asset> insertAllStocks() {
        try {
            return alpacaAPI.assets().get(AssetStatus.ACTIVE, AssetClass.US_EQUITY);
        } catch (AlpacaClientException ae){
            System.out.println(ae.getMessage());
        }
        return null;
    }
}
