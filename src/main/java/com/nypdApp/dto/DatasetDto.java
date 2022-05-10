package com.nypdApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class DatasetDto {

    @JsonProperty("cmplnt_num")
    @Schema(required = true, description = "ID for each complaint ", example = "195224636")
    @Min(value = 1, message = "value of cmplnt_num must be minimum 1")
    @NotNull(message = "value of cmplnt_num must not be null")
    private Long cmplntNum;

    @Schema(description = "Exact date of occurrence for the reported event (or starting date of occurrence, if CMPLNT_TO_DT exists) ")
    @JsonProperty("cmplnt_fr_dt")
    private LocalDateTime cmplntFrDt;

    @Schema(description = "Exact time of occurrence for the reported event (or starting time of occurrence, if CMPLNT_TO_TM exists)")
    @JsonProperty("cmplnt_fr_tm")
    private LocalTime cmplntFrTm;

    @Schema(description = "Ending date of occurrence for the reported event, if exact time of occurrence is unknown")
    @JsonProperty("cmplnt_to_dt")
    private LocalDateTime cmplntToDt;

    @Schema(description = "Ending time of occurrence for the reported event, if exact time of occurrence is unknown")
    @JsonProperty("cmplnt_to_tm")
    private LocalTime cmplntToTm;

    @Schema(description = "The precinct in which the incident occurred")
    @JsonProperty("addr_pct_cd")
    private Integer addrPctCd;

    @Schema(description = "Date event was reported to police")
    @JsonProperty("rpt_dt")
    private LocalDateTime rptDt;

    @JsonProperty("ky_cd")
    @Schema(required = true, description = "Three digit offense classification code", example = "195")
    @Min(value = 100,message = "number must be beetween 100 and 999")
    @Max(value = 999, message = "number must be beetween 100 and 999")
    @NotNull(message = "value of ky_cd must not be null")
    private Integer kyCd;

    @Schema(description = "Description of offense corresponding with key code")
    @JsonProperty("ofns_desc")
    private String ofnsDesc;

    @Schema(description = "Three digit internal classification code (more granular than Key Code)")
    @JsonProperty("pd_cd")
    private Double pdCd;

    @Schema(description = "Description of internal classification corresponding with PD code (more granular than Offense Description)")
    @JsonProperty("pd_desc")
    private String pdDesc;

    @Schema(description = "Indicator of whether crime was successfully completed or attempted, but failed or was interrupted prematurely")
    @JsonProperty("crm_atpt_cptd_cd")
    private String crmAtptCptdCd;


    @Schema(description = "Level of offense: felony, misdemeanor, violation")
    @JsonProperty("law_cat_cd")
    private String lawCatCd;

    @Schema(description = "The name of the borough in which the incident occurred")
    @JsonProperty("boro_nm")
    private String boroNm;

    @Schema(description = "Specific location of occurrence in or around the premises; inside, opposite of, front of, rear of")
    @JsonProperty("loc_of_occur_desc")
    private String locOfOccurDesc;

    @Schema(description = "Specific location of occurrence in or around the premises; inside, opposite of, front of, rear of")
    @JsonProperty("parks_nm")
    private String parksNm;

    @Schema(description = "Specific description of premises; grocery store, residence, street, etc.")
    @JsonProperty("prem_typ_desc")
    private String premTypDesc;

    @Schema(description = "Description of the jurisdiction code")
    @JsonProperty("juris_desc")
    private String jurisDesc;

    @Schema(description = "Jurisdiction responsible for incident. Either internal, like Police(0), Transit(1), and Housing(2);" +
            " or external(3), like Correction, Port Authority, etc.")
    @JsonProperty("jurisdiction_code")
    private Double jurisdictionCode;

    @Schema(description = "Name of NYCHA housing development of occurrence, if applicable")
    @JsonProperty("hadevelopt")
    private String hadevelopt;

    @Schema(description = "Development Level Code")
    @JsonProperty("housing_psa")
    private String housingPsa;

    @Schema(description = "X-coordinate for New York State Plane Coordinate System, Long Island Zone, NAD 83, units feet (FIPS 3104)")
    @JsonProperty("x_coord_cd")
    private Integer xCoordCd;

    @Schema(description = "Y-coordinate for New York State Plane Coordinate System, Long Island Zone, NAD 83, units feet (FIPS 3104)")
    @JsonProperty("y_coord_cd")
    private Integer yCoordCd;

    @Schema(description = "Midblock Latitude coordinate for Global Coordinate System, WGS 1984, decimal degrees (EPSG 4326)")
    @JsonProperty("latitude")
    private Double latitude;

    @Schema(description = "Midblock Longitude coordinate for Global Coordinate System, WGS 1984, decimal degrees (EPSG 4326)")
    @JsonProperty("longitude")
    private Double longitude;

    @Schema(description = "The name of the patrol borough in which the incident occurred")
    @JsonProperty("patrol_boro")
    private String patrolBoro;

    @Schema(description = "Victim’s Age Group")
    @JsonProperty("vic_age_group")
    private String vicAgeGroup;

    @Schema(description = "Victim’s Race Description")
    @JsonProperty("vic_race")
    private String vicRace;

    @Schema(description = "Victim’s Sex Description")
    @JsonProperty("vic_sex")
    private String vicSex;


}


