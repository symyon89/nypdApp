package com.nypdApp.service;

import com.nypdApp.dto.DatasetDto;
import com.nypdApp.model.Dataset;
import com.nypdApp.repository.DatasetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DatasetServiceTest {

    @Mock
    DatasetRepository datasetRepository;

    @Mock
    ModelMapper mapper;

    @InjectMocks
    DatasetService datasetService;


    @Test
    void saveAll() {

        DatasetDto datasetDtoTest = new DatasetDto();
        DatasetDto datasetDtoTest2 = new DatasetDto();
        List<DatasetDto> expectedListDto = List.of(datasetDtoTest,datasetDtoTest2);

        datasetService.saveAll(expectedListDto);
        verify(datasetRepository).saveAll(any());
    }

    @Test
    void findAll() {
        datasetService.findAll();
        verify(datasetRepository).findAll();
    }

    @Test
    void deleteById_returns_false() {
        boolean expectedResult = false;
        boolean result = datasetService.deleteById(123L);
        assertEquals(result,expectedResult);
    }
    @Test
    void deleteById_returns_true() {
        when(datasetRepository.existsById(123L)).thenReturn(true);
        boolean expectedResult = true;
        boolean result = datasetService.deleteById(123L);
        assertEquals(result,expectedResult);
    }

    @Test
    void save() {
        DatasetDto datasetDto = new DatasetDto();
        datasetDto.setCmplntNum(123L);
        datasetDto.setKyCd(123);

        Dataset returnedDataset = new Dataset();
        returnedDataset.setCmplntNum(123L);
        returnedDataset.setKyCd(123);

        when(datasetRepository.save(any())).thenReturn(returnedDataset);
        datasetService.save(datasetDto);
        verify(datasetRepository).save(any());
    }

    @Test
    void findAllOffenses() {
        datasetService.findAllOffenses();
        verify(datasetRepository).findAll(Sort.by(Sort.Direction.ASC, "kyCd"));
    }

    @Test
    void findAllOffensesGroupedBy() {
        DatasetDto datasetDto = new DatasetDto();
        datasetDto.setCmplntNum(123L);
        datasetDto.setKyCd(123);

        List<DatasetDto> datasetDtos = List.of(datasetDto);

        Dataset dataset = new Dataset();
        dataset.setCmplntNum(123L);
        dataset.setKyCd(123);

        List<Dataset> datasets = List.of(dataset);

        when(datasetService.findAllOffenses()).thenReturn(datasetDtos);
        Map<Integer, List<DatasetDto>> result = datasetService.findAllOffensesGroupedBy();
        assertEquals(result.size(),1);
    }
}
