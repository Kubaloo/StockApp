package com.master.Stockapp.Stockapp.controller;

import com.master.Stockapp.Stockapp.dto.StockDto;
import com.master.Stockapp.Stockapp.dto.mapper.StockDtoMapper;
import com.master.Stockapp.Stockapp.model.Stock;
import com.master.Stockapp.Stockapp.repository.StockRepository;
import com.master.Stockapp.Stockapp.service.StockService;
import lombok.RequiredArgsConstructor;
import net.jacobpeterson.alpaca.model.endpoint.assets.Asset;
import net.jacobpeterson.alpaca.model.endpoint.assets.enums.AssetStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockRepository stockRepository;
    private final StockService stockService;
    private final StockDtoMapper stockDtoMapper;

    @GetMapping
    public ResponseEntity<Collection<StockDto>> getAllStocks() {
        List<Stock> allStocks = stockRepository.findAll();
        List<StockDto> result = allStocks.stream()
                .map(stockDtoMapper::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public void insertAllStocks() {
        List<Asset> assets = stockService.insertAllStocks();
        List<String> symbols = assets.stream().map(Asset::getSymbol).collect(Collectors.toList());
        for (Asset asset : assets) {
            if(asset.getTradable() && asset.getStatus() == AssetStatus.ACTIVE &&  !symbols.contains(asset.getSymbol())) {
                Stock stock = new Stock();
                stock.setCompany(asset.getName());
                stock.setSymbol(asset.getSymbol());
                stockRepository.save(stock);
                System.out.println("Added a new stock " + asset.getSymbol() + " " + asset.getName());
            }
        }
    }
}
