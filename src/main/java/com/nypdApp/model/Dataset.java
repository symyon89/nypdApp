package com.nypdApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Dataset {

    @Id
    private Long cmplntNum;
    private LocalDateTime cmplntFrDt;
    private LocalTime cmplntFrTm;
    private LocalDateTime cmplntToDt;
    private LocalTime cmplntToTm;
    private Integer addrPctCd;
    private LocalDateTime rptDt;
    private Integer kyCd;
    private String ofnsDesc;
    private Double pdCd;
    private String pdDesc;
    private String crmAtptCptdCd;
    private String lawCatCd;
    private String boroNm;
    private String locOfOccurDesc;
    private String parksNm;
    private String premTypDesc;
    private String jurisDesc;
    private Double jurisdictionCode;
    private String hadevelopt;
    private String housingPsa;
    private Integer xCoordCd;
    private Integer yCoordCd;
    private Double latitude;
    private Double longitude;
    private String patrolBoro;
    private String vicAgeGroup;
    private String vicRace;
    private String vicSex;
    private Integer computedRegionEfshH5xi;
    private Integer computedRegionF5dnYrer;
    private Integer computedRegionYejiBk3q;
    private Integer computedRegion92fq4b7q;
    private Integer computedRegionSbqjEnih;

}
