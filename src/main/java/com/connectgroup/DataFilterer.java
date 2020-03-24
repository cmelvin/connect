package com.connectgroup;

import com.connectgroup.model.DataFilterDo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataFilterer extends AbstractDataFilterer {

    private static final String COMMA = ",";
    //hello

    //thsi si world

    public static Collection<?> filterByCountry(Reader source, String country) {
        List<DataFilterDo> dataFilterDoList = convertInputFile(source);
        return dataFilterDoList.stream().filter(dataLine -> dataLine.getCountryCode().equals(country)).collect(Collectors.toList());
    }

    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
        List<DataFilterDo> dataFilterDoList = convertInputFile(source);
        return dataFilterDoList.stream()
                .filter(dataLine -> dataLine.getCountryCode().equalsIgnoreCase(country))
                .filter(dataLine -> (dataLine.getResponseTime() > limit))
                .collect(Collectors.toList());

    }

    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
        List<DataFilterDo> dataFilterDoList = convertInputFile(source);
        Double avgResponsepTime = dataFilterDoList.stream()
                .mapToLong(DataFilterDo::getResponseTime).average().getAsDouble();
        return dataFilterDoList.stream()
                .filter(dataLine -> dataLine.getResponseTime() > avgResponsepTime).collect(Collectors.toList());

    }

    private static List<DataFilterDo> convertInputFile(final Reader source) {
        List<DataFilterDo> inputList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(source)) {
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
        } catch (IOException e) {
           //do nothing
        }
        return inputList;
    }

    private static Function<String, DataFilterDo> mapToItem = line -> {
        String[] p = line.split(COMMA);
        DataFilterDo item = new DataFilterDo();
        for (String lineItem : p) {
            item =populate(item, lineItem);
        }
        return item;
    };

}