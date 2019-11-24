package com.connectgroup.model;

import java.util.Objects;

public class DataFilterDo {


    Long requestTime;
    String countryCode;
    Long responseTime;


    public DataFilterDo() {

    }

    public DataFilterDo(Long aLong, String gb, Long aLong1) {
        this.requestTime = aLong;
        this.countryCode = gb;
        this.responseTime = aLong1;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataFilterDo that = (DataFilterDo) o;
        return requestTime.equals(that.requestTime) &&
                countryCode.equals(that.countryCode) &&
                responseTime.equals(that.responseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestTime, countryCode, responseTime);
    }

    @Override
    public String toString() {
        return "DataFilterDo{" +
                "requestTime=" + requestTime +
                ", countryCode='" + countryCode + '\'' +
                ", responseTime=" + responseTime +
                '}';
    }
}
