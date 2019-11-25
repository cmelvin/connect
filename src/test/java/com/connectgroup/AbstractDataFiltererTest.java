package com.connectgroup;

import com.connectgroup.model.DataFilterDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDataFiltererTest {

    protected static final String EMPTY_LOC="src/test/resources/empty";
    protected static final String SINGLE_LOC="src/test/resources/single-line";
    protected static final String MULTI_LOC="src/test/resources/multi-lines";
    protected static final String JUMBLED_LOC="src/test/resources/jumbled-lines";
    protected static final String US="US";
    protected static final String GB="GB";
    protected static final String KS="KS";


    protected List<DataFilterDo> createDataFilterDoSingleCountry() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1431592497L, GB, 200L));
        return dataFilterDos;
    }

    protected List<DataFilterDo> createDataFilterDoSingleCountryMismatch() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1431592497L, KS, 200L));
        return dataFilterDos;
    }


    protected List<DataFilterDo> createDataFilterDoMultipleCountries() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1433190845L, US, 539L));
        dataFilterDos.add(createDataFilterDo(1433666287L, US, 789L));
        dataFilterDos.add(createDataFilterDo(1432484176L, US, 850L));
        return dataFilterDos;
    }

    protected List<DataFilterDo> createDataFilterDoMultipleCountriesMismatch() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1433190845L, GB, 539L));
        dataFilterDos.add(createDataFilterDo(1433666287L, GB, 789L));
        dataFilterDos.add(createDataFilterDo(1432484176L, GB, 850L));
        return dataFilterDos;
    }

    protected DataFilterDo createDataFilterDo(Long reqTime, String countryCode, Long respTime) {
        DataFilterDo dataFilterDo = new DataFilterDo();
        dataFilterDo.setRequestTime(reqTime);
        dataFilterDo.setCountryCode(countryCode);
        dataFilterDo.setResponseTime(respTime);
        return dataFilterDo;
    }

    protected List<DataFilterDo> createDataFilterDoMultipleCountriesWithResponseTimeAboveLimit() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1433666287L, US, 789L));
        dataFilterDos.add(createDataFilterDo(1432484176L, US, 850L));
        return dataFilterDos;
    }

    protected List<DataFilterDo> createDataFilterDoMultipleCountriesWithResponseTimeBelowLimit() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1433190845L, US, 539L));
        return dataFilterDos;
    }

    protected List<DataFilterDo> createDataFilterDoMultipleCountriesWithResponseTimeAboveAverage() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1433190845L, US, 539L));
        dataFilterDos.add(createDataFilterDo(1433666287L, US, 789L));
        dataFilterDos.add(createDataFilterDo(1432484176L, US, 850L));
        return dataFilterDos;
    }

    protected FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }

}
