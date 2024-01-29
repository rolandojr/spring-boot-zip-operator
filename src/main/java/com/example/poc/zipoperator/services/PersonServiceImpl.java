package com.example.poc.zipoperator.services;

import com.example.poc.zipoperator.configuration.ApplicationProperties;
import com.example.poc.zipoperator.model.*;
import com.example.poc.zipoperator.repository.PersonRepositoty;
import com.example.poc.zipoperator.utils.Extrafields;
import com.example.poc.zipoperator.utils.Utils;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.poc.zipoperator.utils.Utils.*;

@Slf4j
@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepositoty personRepositoty;
    private ApplicationProperties properties;

    // Helper methods

    @Override
    public Single<PersonData> findPersonById(String personId, List<String> extraFields) {
        List<String> extrafieldsNoImplemented = extraFields.stream().filter(field -> !properties.getExtraFields().get(field)).collect(Collectors.toList());
        if (extrafieldsNoImplemented.isEmpty()) {
            return searchPersonCallDb2(personId, extraFields);
        }
        return searchPersonCallPrtw(personId, extraFields);
    }

    private Single<PersonData> searchPersonCallPrtw(String personId, List<String> extraFields) {
        return Single.fromCallable(() -> {
            log.info("Calling to Prtw {}", Thread.currentThread().getName());
            PersonData personData = new PersonData();
            populateUserBasic(personData, personId, extraFields);
            populateAddressBasic(personData, personId, extraFields);
            populateThirdPartyBasic(personData, personId, extraFields);
            populateDemographicInformationBasic(personData, personId, extraFields);
            return personData;
        }).subscribeOn(Schedulers.io());
    }

    private Single<PersonData> searchPersonCallDb2(String personId, List<String> extraFields) {
        log.info("Calling to Db2 {}", Thread.currentThread().getName());
        PersonData personData = new PersonData();
        Single<UserBasic> userBasicSingle = getBasicEntity(
                extraFields,
                Extrafields.BASIC,
                personRepositoty::findBasicPerson,
                new UserBasic(),
                personId);

        Single<AddressBasic> addressBasicSingle = getBasicEntity(
                extraFields,
                Extrafields.ADDRESSES,
                personRepositoty::findBasicAddress,
                new AddressBasic(),
                personId);
        Single<ThirdPartyBasic> thirdPartyBasicSingle = getBasicEntity(
                extraFields,
                Extrafields.THIRDPARTY,
                personRepositoty::findBasicThirdParty,
                new ThirdPartyBasic(),
                personId);

        Single<DemographicInformationBasic> demographicInformationBasicSingle = getBasicEntity(
                extraFields,
                Extrafields.DEMOGRAPHICINFORMATION,
                personRepositoty::findDemoInformationBasic,
                new DemographicInformationBasic(),
                personId);

        return Single.zip(
                userBasicSingle,
                addressBasicSingle,
                thirdPartyBasicSingle,
                demographicInformationBasicSingle,
                (userBasic, addressBasic, thirdPartyBasic, demographicInformationBasic) -> {
                    personData.setUserBasic(userBasic);
                    personData.setAddressBasic(addressBasic);
                    personData.setThirdPartyBasic(thirdPartyBasic);
                    personData.setDemoInformationBasic(demographicInformationBasic);
                    return personData;
                }).subscribeOn(Schedulers.io());

    }

    private <T> Single<T> getBasicEntity(
            List<String> extraFields,
            Extrafields field,
            Function<String, Single<T>> repositoryFunction,
            T defaultEntity,
            String personId) {
        return extraFields.contains(field.getName()) ?
                repositoryFunction.apply(personId) :
                Single.just(defaultEntity);
    }


}
