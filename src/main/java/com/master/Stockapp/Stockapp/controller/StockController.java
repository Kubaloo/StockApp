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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/insert")
    public ResponseEntity insertAllStocks() {
        List<Asset> assets = stockService.insertAllStocks();
        for (Asset asset : assets) {
            if (asset.getTradable() && asset.getStatus() == AssetStatus.ACTIVE) {
                Stock stock = new Stock();
                stock.setCompany(asset.getName());
                stock.setSymbol(asset.getSymbol());
                stockRepository.save(stock);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity removeAllStocks() {
        stockRepository.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updateStocks() {
        List<Asset> assets = stockService.insertAllStocks();
        List<Stock> allStocks = stockRepository.findAll();
        List<String> symbols = allStocks.stream().map(Stock::getSymbol).collect(Collectors.toList());
        for (Asset asset : assets) {
            if (asset.getTradable() && asset.getStatus() == AssetStatus.ACTIVE && !symbols.contains(asset.getSymbol())) {
                Stock stock = new Stock();
                stock.setCompany(asset.getName());
                stock.setSymbol(asset.getSymbol());
                stockRepository.save(stock);
                System.out.println("Added a new stock " + asset.getSymbol() + " " + asset.getName());
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
