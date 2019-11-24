package com.connectgroup.model;

import com.connectgroup.utils.EqualsTester;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DataFilterDoTest {


    @Test
    public void testGetSetRequestTime() {
        DataFilterDo dataFilterDo = new DataFilterDo();
        dataFilterDo.setRequestTime(1234567891011121314L);
        assertEquals(1234567891011121314L, dataFilterDo.requestTime.longValue());

    }

    @Test
    public void testGetSetResponseTime() {
        DataFilterDo dataFilterDo = new DataFilterDo();
        dataFilterDo.setResponseTime(1234567891011121314L);
        assertEquals(1234567891011121314L, dataFilterDo.responseTime.longValue());
    }

    @Test
    public void testGetSetCountrytCode() {
        DataFilterDo dataFilterDo = new DataFilterDo();
        dataFilterDo.setCountryCode("GB");
        assertEquals("GB", dataFilterDo.getCountryCode().toUpperCase());
    }

    @Test
    public void testEqualsAndHashCode() {
        EqualsTester<DataFilterDo> equalsTester =
                EqualsTester.newInstance(new DataFilterDo(1234567891011121314L, "GB", 1234567891011121314L));

        equalsTester.assertEqual(new DataFilterDo(1234567891011121314L, "GB", 1234567891011121314L),
                new DataFilterDo(1234567891011121314L, "GB", 1234567891011121314L));

        equalsTester.assertNotEqual(new DataFilterDo(1234567891011121314L, "GB", 1234567891011121314L),
                new DataFilterDo(1235L, "GB", 1235L));

    }

    @Test
    public void testToString() {
        DataFilterDo dataFilterDo = new DataFilterDo(1234567891011121314L, "GB", 1234567891011121314L);
        String x = dataFilterDo.toString();
        String expected = "DataFilterDo{requestTime=1234567891011121314, countryCode='GB', responseTime=1234567891011121314}";
        assertEquals(expected, dataFilterDo.toString());
    }

}
