package com.master.Stockapp.Stockapp.dto.mapper;

import com.master.Stockapp.Stockapp.dto.StockDto;
import com.master.Stockapp.Stockapp.model.Stock;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockDtoMapper {
    private final ModelMapper modelMapper;

    public StockDto convertToDto(Stock s){
        return modelMapper.map(s, StockDto.class);
    }

    public Stock convertToEntity(StockDto dto) {
        return modelMapper.map(dto, Stock.class);
    }


}
