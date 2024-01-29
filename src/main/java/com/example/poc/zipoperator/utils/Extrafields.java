package com.example.poc.zipoperator.utils;

import lombok.Getter;

@Getter
public enum Extrafields {

    BASIC("basic"),
    ADDRESSES("addresses"),
    THIRDPARTY("thirdparty"),
    DEMOGRAPHICINFORMATION("demographicInformation");

    private String name;

    Extrafields(String name) {
        this.name = name;
    }

}
