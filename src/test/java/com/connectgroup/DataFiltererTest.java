package com.connectgroup;

import com.connectgroup.model.DataFilterDo;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class DataFiltererTest {

    private static final String EMPTY_LOC="src/test/resources/empty";
    private static final String SINGLE_LOC="src/test/resources/single-line";
    private static final String MULTI_LOC="src/test/resources/multi-lines";
    private static final String JUMBLED_LOC="src/test/resources/jumbled-lines";
    private static final String US="US";
    private static final String GB="GB";
    private static final String KS="KS";
    @Test
    public void shouldReturnEmptyCollection_WhenLogFileIsEmpty() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountry(openFile(EMPTY_LOC), GB).isEmpty());
    }

    @Test
    public void shouldFilterByCountry_WhenLogFileIsNotEmptyForSingleCountryFromSingleFile() throws FileNotFoundException {
        assertEquals(DataFilterer.filterByCountry(openFile(SINGLE_LOC), GB), createDataFilterDoSingleCountry());
    }

    @Test
    public void shouldFilterByCountry_WhenLogFileIsNotEmptyForSingleCountryFromSingleFileWithCountryMismatch() throws FileNotFoundException {
        assertNotEquals(DataFilterer.filterByCountry(openFile(MULTI_LOC), GB), createDataFilterDoSingleCountryMismatch());
    }

    @Test
    public void shouldFilterByCountry_WhenLogFileIsNotEmptyForMultipleCountries() throws FileNotFoundException {
        assertEquals(DataFilterer.filterByCountry(openFile(MULTI_LOC), US), createDataFilterDoMultipleCountries());
    }

    @Test
    public void shouldFilterByCountry_WhenLogFileIsNotEmptyForMultipleCountriesCountryMismatch() throws FileNotFoundException {
        assertNotEquals(DataFilterer.filterByCountry(openFile(MULTI_LOC), US), createDataFilterDoMultipleCountriesMismatch());
    }

    @Test
    public void shouldFilterByCountry_WhenLogFileIsNotEmptyForJumbledCountries() throws FileNotFoundException {
        assertEquals(DataFilterer.filterByCountry(openFile(JUMBLED_LOC), US), createDataFilterDoMultipleCountries());
    }

    @Test
    public void shouldFilterByCountry_WhenLogFileIsNotEmptyForMultipleCountriesCountryWithResponseTimeAboveLimit() throws FileNotFoundException {
        assertEquals(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile(MULTI_LOC), US, 600L),
                createDataFilterDoMultipleCountriesWithResponseTimeAboveLimit());
    }

    @Test
    public void shouldFilterByCountry_WhenLogFileIsNotEmptyForMultipleCountriesCountryWithResponseTimeBelowLimit() throws FileNotFoundException {
        assertNotEquals(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile(MULTI_LOC), US, 600L),
                createDataFilterDoMultipleCountriesWithResponseTimeBelowLimit());
    }


    @Test
    public void shouldFilterByCountry_WhenLogFileIsNotEmptyForMultipleCountriesCountryWithResponseTimeAboveAverage() throws FileNotFoundException {
        assertEquals(DataFilterer.filterByResponseTimeAboveAverage(openFile(MULTI_LOC)),
                createDataFilterDoMultipleCountriesWithResponseTimeAboveAverage());
    }

    private List<DataFilterDo> createDataFilterDoSingleCountry() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1431592497L, GB, 200L));
        return dataFilterDos;
    }

    private List<DataFilterDo> createDataFilterDoSingleCountryMismatch() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1431592497L, KS, 200L));
        return dataFilterDos;
    }


    private List<DataFilterDo> createDataFilterDoMultipleCountries() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1433190845L, US, 539L));
        dataFilterDos.add(createDataFilterDo(1433666287L, US, 789L));
        dataFilterDos.add(createDataFilterDo(1432484176L, US, 850L));
        return dataFilterDos;
    }

    private List<DataFilterDo> createDataFilterDoMultipleCountriesMismatch() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1433190845L, GB, 539L));
        dataFilterDos.add(createDataFilterDo(1433666287L, GB, 789L));
        dataFilterDos.add(createDataFilterDo(1432484176L, GB, 850L));
        return dataFilterDos;
    }

    private DataFilterDo createDataFilterDo(Long reqTime, String countryCode, Long respTime) {
        DataFilterDo dataFilterDo = new DataFilterDo();
        dataFilterDo.setRequestTime(reqTime);
        dataFilterDo.setCountryCode(countryCode);
        dataFilterDo.setResponseTime(respTime);
        return dataFilterDo;
    }

    private List<DataFilterDo> createDataFilterDoMultipleCountriesWithResponseTimeAboveLimit() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1433666287L, US, 789L));
        dataFilterDos.add(createDataFilterDo(1432484176L, US, 850L));
        return dataFilterDos;
    }

    private List<DataFilterDo> createDataFilterDoMultipleCountriesWithResponseTimeBelowLimit() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1433190845L, US, 539L));
        return dataFilterDos;
    }

    private List<DataFilterDo> createDataFilterDoMultipleCountriesWithResponseTimeAboveAverage() {
        List<DataFilterDo> dataFilterDos = new ArrayList<>();
        dataFilterDos.add(createDataFilterDo(1433190845L, US, 539L));
        dataFilterDos.add(createDataFilterDo(1433666287L, US, 789L));
        dataFilterDos.add(createDataFilterDo(1432484176L, US, 850L));
        return dataFilterDos;
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }


}
