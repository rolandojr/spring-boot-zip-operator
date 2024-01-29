package com.example.poc.zipoperator.repository;

import com.example.poc.zipoperator.model.AddressBasic;
import com.example.poc.zipoperator.model.DemographicInformationBasic;
import com.example.poc.zipoperator.model.ThirdPartyBasic;
import com.example.poc.zipoperator.model.UserBasic;
import io.reactivex.Single;

public interface PersonRepositoty {

    Single<UserBasic> findBasicPerson(String personId);

    Single<AddressBasic> findBasicAddress(String personId);

    Single<ThirdPartyBasic> findBasicThirdParty(String personId);

    Single<DemographicInformationBasic> findDemoInformationBasic(String personId);
}
