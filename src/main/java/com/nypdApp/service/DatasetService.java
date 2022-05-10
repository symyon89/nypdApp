package com.nypdApp.service;

import com.nypdApp.dto.DatasetDto;
import com.nypdApp.exception.ServerErrorException;
import com.nypdApp.model.Dataset;
import com.nypdApp.repository.DatasetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Validated
@Slf4j
@RequiredArgsConstructor
@Service
public class DatasetService {
    private final WebClient builder;
    private final DatasetRepository datasetRepository;
    private final ModelMapper mapper;

    @PostConstruct
    @Transactional
    public void insertInDatabase() {
        List<DatasetDto> datasetDtos = getDataset();
        if (datasetRepository.count() == 0 && datasetDtos != null) {
            log.info(String.valueOf(datasetDtos.size()));
            saveAll(datasetDtos);

        }
    }

    public void saveAll(List<DatasetDto> datasetDtoList) {
        datasetRepository.saveAll(mapper.map(datasetDtoList, new TypeToken<ArrayList<Dataset>>() {
        }.getType()));
    }

    public List<DatasetDto> findAll() {
        return mapper.map(datasetRepository.findAll(),new TypeToken<ArrayList<DatasetDto>>() {
        }.getType());
    }

    public boolean deleteById(Long id) {
        if(datasetRepository.existsById(id)) {
            datasetRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public DatasetDto save(@Valid DatasetDto datasetDto) {
        return mapper.map( datasetRepository.save(mapper.map(datasetDto,Dataset.class)),DatasetDto.class);
    }

    public List<DatasetDto> findAllOffenses() {
        return mapper.map(datasetRepository.findAll(Sort.by(Sort.Direction.ASC, "kyCd")),new TypeToken<ArrayList<DatasetDto>>() {
        }.getType());
    }

    public Map<Integer, List<DatasetDto>> findAllOffensesGroupedBy() {
        return findAllOffenses().stream().collect(groupingBy(DatasetDto::getKyCd));
    }

    private List<DatasetDto> getDataset() {
        return builder.get()
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new ServerErrorException("Error 400 from NYPD Server")))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new ServerErrorException("Error 500 from NYPD Server")))
                .bodyToFlux(DatasetDto.class)
                .takeLast(100)
                .collectList()
                .block();
    }

}
