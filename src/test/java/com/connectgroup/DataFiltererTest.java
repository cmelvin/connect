package com.connectgroup;

import com.connectgroup.model.DataFilterDo;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class DataFiltererTest extends AbstractDataFiltererTest {


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

}
