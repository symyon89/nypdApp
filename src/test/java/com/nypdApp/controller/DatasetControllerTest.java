package com.nypdApp.controller;

import com.nypdApp.dto.DatasetDto;
import com.nypdApp.model.Dataset;
import com.nypdApp.repository.DatasetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DatasetControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DatasetRepository datasetRepository;
    private static Dataset datasetTest1;
    private static Dataset datasetTest2;

    @BeforeAll
    public static void asignValusToDataset() {
        datasetTest1 = new Dataset();
        datasetTest2 = new Dataset();
        datasetTest1.setCmplntNum(123465L);
        datasetTest1.setKyCd(100);
        datasetTest2.setCmplntNum(111111L);
        datasetTest2.setKyCd(200);
    }

    @BeforeEach
    public void cleanUpDatabase() {
        datasetRepository.deleteAll();

    }

    @Test
    void findAll() throws Exception {
        datasetRepository.save(datasetTest1);
        datasetRepository.save(datasetTest2);
        mvc.perform(get("/dataset/stats/total")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"cmplnt_num\":111111,\"cmplnt_fr_dt\":null,\"cmplnt_fr_tm\":null," +
                        "\"cmplnt_to_dt\":null,\"cmplnt_to_tm\":null,\"addr_pct_cd\":null,\"rpt_dt\":null," +
                        "\"ky_cd\":200,\"ofns_desc\":null,\"pd_cd\":null,\"pd_desc\":null,\"crm_atpt_cptd_cd\":null," +
                        "\"law_cat_cd\":null,\"boro_nm\":null,\"loc_of_occur_desc\":null,\"parks_nm\":null,\"prem_typ_desc\":null," +
                        "\"juris_desc\":null,\"jurisdiction_code\":null,\"hadevelopt\":null,\"housing_psa\":null,\"x_coord_cd\":null," +
                        "\"y_coord_cd\":null,\"latitude\":null,\"longitude\":null,\"patrol_boro\":null,\"vic_age_group\":null,\"vic_race\":null," +
                        "\"vic_sex\":null},{\"cmplnt_num\":123465,\"cmplnt_fr_dt\":null,\"cmplnt_fr_tm\":null,\"cmplnt_to_dt\":null,\"cmplnt_to_tm\":null," +
                        "\"addr_pct_cd\":null,\"rpt_dt\":null,\"ky_cd\":100,\"ofns_desc\":null,\"pd_cd\":null,\"pd_desc\":null,\"crm_atpt_cptd_cd\":null,\"law_cat_cd\":null," +
                        "\"boro_nm\":null,\"loc_of_occur_desc\":null,\"parks_nm\":null,\"prem_typ_desc\":null," +
                        "\"juris_desc\":null,\"jurisdiction_code\":null,\"hadevelopt\":null,\"housing_psa\":null," +
                        "\"x_coord_cd\":null,\"y_coord_cd\":null,\"latitude\":null,\"longitude\":null,\"patrol_boro\":null," +
                        "\"vic_age_group\":null,\"vic_race\":null,\"vic_sex\":null}]"));
    }

    @Test
    void findAllOffenses() throws Exception {
        datasetRepository.save(datasetTest1);
        datasetRepository.save(datasetTest2);
        mvc.perform(get("/dataset/stats/offenses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"100\":[{\"cmplnt_num\":123465,\"cmplnt_fr_dt\":null,\"cmplnt_fr_tm\":null" +
                        ",\"cmplnt_to_dt\":null,\"cmplnt_to_tm\":null,\"addr_pct_cd\":null,\"rpt_dt\":null,\"ky_cd\":100," +
                        "\"ofns_desc\":null,\"pd_cd\":null,\"pd_desc\":null,\"crm_atpt_cptd_cd\":null,\"law_cat_cd\":null," +
                        "\"boro_nm\":null,\"loc_of_occur_desc\":null,\"parks_nm\":null,\"prem_typ_desc\":null,\"juris_desc\":null," +
                        "\"jurisdiction_code\":null,\"hadevelopt\":null,\"housing_psa\":null,\"x_coord_cd\":null,\"y_coord_cd\":null," +
                        "\"latitude\":null,\"longitude\":null,\"patrol_boro\":null,\"vic_age_group\":null,\"vic_race\":null,\"vic_sex\":null}]" +
                        ",\"200\":[{\"cmplnt_num\":111111,\"cmplnt_fr_dt\":null,\"cmplnt_fr_tm\":null,\"cmplnt_to_dt\":null," +
                        "\"cmplnt_to_tm\":null,\"addr_pct_cd\":null,\"rpt_dt\":null,\"ky_cd\":200,\"ofns_desc\":null,\"pd_cd\":null," +
                        "\"pd_desc\":null,\"crm_atpt_cptd_cd\":null,\"law_cat_cd\":null,\"boro_nm\":null,\"loc_of_occur_desc\":null," +
                        "\"parks_nm\":null,\"prem_typ_desc\":null,\"juris_desc\":null,\"jurisdiction_code\":null,\"hadevelopt\":null," +
                        "\"housing_psa\":null,\"x_coord_cd\":null,\"y_coord_cd\":null,\"latitude\":null,\"longitude\":null,\"patrol_boro\":null," +
                        "\"vic_age_group\":null,\"vic_race\":null,\"vic_sex\":null}]}"));
    }

    @Test
    void save() throws Exception {
        DatasetDto dataDto = new DatasetDto();
        dataDto.setCmplntNum(1L);
        dataDto.setKyCd(123);
        mvc.perform(post("/dataset")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataDto)))
                .andExpect(status().isOk());

    }

    @Test
    void deleteById() throws Exception{
        datasetRepository.save(datasetTest1);
        mvc.perform(delete("/dataset/123465")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().string("true"));
    }
}
