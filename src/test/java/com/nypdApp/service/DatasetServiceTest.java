package com.nypdApp.service;

import com.nypdApp.dto.DatasetDto;
import com.nypdApp.repository.DatasetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DatasetServiceTest {

    @Mock
    DatasetRepository datasetRepository;

    @Spy
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
    void deleteById() {
    }

    @Test
    void save() {
    }

    @Test
    void findAllOffenses() {
    }

    @Test
    void findAllOffensesGroupedBy() {
    }
}
