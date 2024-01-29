package com.example.poc.zipoperator.repository;

import com.example.poc.zipoperator.model.AddressBasic;
import com.example.poc.zipoperator.model.DemographicInformationBasic;
import com.example.poc.zipoperator.model.ThirdPartyBasic;
import com.example.poc.zipoperator.model.UserBasic;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class PersonRepositoryImpl implements PersonRepositoty {

    @Override
    public Single<UserBasic> findBasicPerson(String personId) {

        return Single.fromCallable(() -> {
            log.info("Thread findBasicPerson " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e) {
                e.printStackTrace();
            }
            UserBasic userBasic = new UserBasic();
            userBasic.setPersonId(personId);
            userBasic.setCic("00120212");
            userBasic.setFirstname("Jhon");
            userBasic.setLastname("Doe");
            return userBasic;
        }).subscribeOn(Schedulers.io());

    }

    @Override
    public Single<AddressBasic> findBasicAddress(String personId) {
        return Single.fromCallable(() -> {
            log.info("Thread findBasicAddress " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e) {
                e.printStackTrace();
            }
            AddressBasic addressBasic = new AddressBasic();
            addressBasic.setPersonId(personId);
            addressBasic.setCic("00120212");
            addressBasic.setEmail("jhon.doe@mail.com");
            return addressBasic;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<ThirdPartyBasic> findBasicThirdParty(String personId) {
        return Single.fromCallable(() -> {
            log.info("Thread findBasicThirdParty " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e) {
                e.printStackTrace();
            }
            ThirdPartyBasic thirdPartyBasic = new ThirdPartyBasic();
            thirdPartyBasic.setPersonId(personId);
            thirdPartyBasic.setCic("00120212");
            thirdPartyBasic.setCompany("TCS");
            thirdPartyBasic.setRole("Software Engineer");
            return thirdPartyBasic;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<DemographicInformationBasic> findDemoInformationBasic(String personId) {
        return Single.fromCallable(() -> {
            log.info("Thread findDemoInformationBasic " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(4);
            }catch (Exception e) {
                e.printStackTrace();
            }
            DemographicInformationBasic demographicInformationBasic = new DemographicInformationBasic();
            demographicInformationBasic.setPersonId(personId);
            demographicInformationBasic.setCic("00120212");
            demographicInformationBasic.setGender("Male");
            demographicInformationBasic.setAge("26");
            demographicInformationBasic.setMaritalStatus("Single");
            return demographicInformationBasic;
        }).subscribeOn(Schedulers.io());
    }
}
