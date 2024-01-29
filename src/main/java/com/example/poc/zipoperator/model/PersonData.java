package com.example.poc.zipoperator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonData {

    private UserBasic userBasic;
    private AddressBasic addressBasic;
    private ThirdPartyBasic thirdPartyBasic;
    private DemographicInformationBasic demoInformationBasic;
}
