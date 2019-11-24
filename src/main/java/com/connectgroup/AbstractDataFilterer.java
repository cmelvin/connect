package com.connectgroup;

import com.connectgroup.model.DataFilterDo;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractDataFilterer {

    private static long MINIMUM_TIMESTAMP = 0;


    static {
        MINIMUM_TIMESTAMP = ZonedDateTime.parse(
                "Jan 13 1970 00:00:00.000 UTC",
                DateTimeFormatter.ofPattern("MMM d uuuu HH:mm:ss.SSS z")
        )
                .toInstant()
                .toEpochMilli();
    }


    protected static DataFilterDo populate(DataFilterDo dataFilterDo, String dataLine) {
        if (dataLine != null && dataLine.trim().length() > 0) {
            if (checkIfDataLineIsRequestTimeValidFormat(dataLine)) {
                dataFilterDo.setRequestTime(Long.valueOf(dataLine));
            } else if (checkIfDataLineIsCountryCodeValidFormat(dataLine)) {
                dataFilterDo.setCountryCode(dataLine);
            } else {
                dataFilterDo.setResponseTime(Long.valueOf(dataLine));
            }
        }
        return dataFilterDo;
    }

    private static final boolean checkIfDataLineIsRequestTimeValidFormat(String dataValue) {
        try {
            Long times = Long.valueOf(dataValue);
            return (times >= MINIMUM_TIMESTAMP) && (times <= System.currentTimeMillis());
        } catch (Exception e) {
            //do nothing
        }
        return false;
    }

    private static final boolean checkIfDataLineIsCountryCodeValidFormat(String dataValue) {
        try {
            return dataValue.chars().allMatch(Character::isLetter);
        } catch (Exception e) {
            //do nothing
        }
        return false;
    }

}
