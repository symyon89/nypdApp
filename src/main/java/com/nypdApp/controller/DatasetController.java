package com.nypdApp.controller;

import com.nypdApp.dto.DatasetDto;
import com.nypdApp.service.DatasetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("dataset")
public class DatasetController {

    private final DatasetService datasetService;

    @Operation(summary = "GET request", description = "Get all datasets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @GetMapping("stats/total")
    public List<DatasetDto> findAll() {
        return datasetService.findAll();
    }

    @Operation(summary = "GET request", description = "Get all datasets grouped by ky_pc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    }
    )
    @GetMapping("stats/offenses")
    public Map<Integer, List<DatasetDto>> findAllOffenses() {
        return datasetService.findAllOffensesGroupedBy();
    }

    @Operation(summary = "Post request", description = "save a new dataset")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    }
    )
    @PostMapping
    public DatasetDto save(@RequestBody DatasetDto datasetDto) {
        return datasetService.save(datasetDto);
    }

    @Operation(summary = "Delete request", description = "Delete dataset by id, returns true if success or returns false  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    }
    )
    @DeleteMapping("{id}")
    public boolean deleteById(@PathVariable Long id) {
        return datasetService.deleteById(id);
    }
}
