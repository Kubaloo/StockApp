package com.master.Stockapp.Stockapp;

import com.master.Stockapp.Stockapp.controller.StockController;
import com.master.Stockapp.Stockapp.service.StockService;
import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.assets.Asset;
import net.jacobpeterson.alpaca.model.endpoint.assets.enums.AssetClass;
import net.jacobpeterson.alpaca.model.endpoint.assets.enums.AssetStatus;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class StockappApplicationTests {

    @Autowired
    StockService stockService;

    @Autowired
    StockController stockController;

    ModelMapper modelMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void invoke() {

      stockController.insertAllStocks();
    }



}
