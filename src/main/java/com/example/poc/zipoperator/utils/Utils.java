package com.example.poc.zipoperator.utils;

import com.example.poc.zipoperator.model.*;

import java.util.List;

public class Utils {


    public static void populateUserBasic(PersonData personData, String personId, List<String> extraFields) {
        UserBasic userBasic = new UserBasic();
        if (extraFields.contains(Extrafields.BASIC.getName())) {
            userBasic.setPersonId(personId);
            userBasic.setCic("00120212");
            userBasic.setFirstname("Jhon");
            userBasic.setLastname("Doe");
        }
        personData.setUserBasic(userBasic);
    }

    public static void populateAddressBasic(PersonData personData, String personId, List<String> extraFields) {
        AddressBasic addressBasic = new AddressBasic();
        if (extraFields.contains(Extrafields.ADDRESSES.getName())) {
            addressBasic.setPersonId(personId);
            addressBasic.setCic("00120212");
            addressBasic.setEmail("jhon.doe@mail.com");
        }
        personData.setAddressBasic(addressBasic);
    }

    public static void populateThirdPartyBasic(PersonData personData, String personId, List<String> extraFields) {
        ThirdPartyBasic thirdPartyBasic = new ThirdPartyBasic();
        if (extraFields.contains(Extrafields.THIRDPARTY.getName())) {
            thirdPartyBasic.setPersonId(personId);
            thirdPartyBasic.setCic("00120212");
            thirdPartyBasic.setCompany("TCS");
            thirdPartyBasic.setRole("Software Engineer");
        }
        personData.setThirdPartyBasic(thirdPartyBasic);
    }

    public static void populateDemographicInformationBasic(PersonData personData, String personId, List<String> extraFields) {
        DemographicInformationBasic demographicInformationBasic = new DemographicInformationBasic();
        if (extraFields.contains(Extrafields.DEMOGRAPHICINFORMATION.getName())) {
            demographicInformationBasic.setPersonId(personId);
            demographicInformationBasic.setCic("00120212");
            demographicInformationBasic.setGender("Male");
            demographicInformationBasic.setAge("26");
            demographicInformationBasic.setMaritalStatus("Single");
        }
        personData.setDemoInformationBasic(demographicInformationBasic);
    }

}
