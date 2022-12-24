package org.example;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class ValCurs {
    @JacksonXmlProperty(isAttribute = true, localName = "Date")
    private String Date;
    @JacksonXmlProperty(isAttribute = true)
    private String name;
    @JacksonXmlProperty(localName = "Valute")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Valute> valuteList;

    public ValCurs(){

    }

    public String getDate() {
        return Date;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ValCurs{" +
                "Date='" + Date + '\'' +
                ", name='" + name + '\'' +
                ", valuteList=" + valuteList +
                '}';
    }

    public List<Valute> getValuteList() {
        return valuteList;
    }
}