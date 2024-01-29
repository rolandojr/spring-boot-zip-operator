package com.example.poc.zipoperator.services;

import com.example.poc.zipoperator.configuration.ApplicationProperties;
import com.example.poc.zipoperator.model.*;
import com.example.poc.zipoperator.repository.PersonRepositoty;
import com.example.poc.zipoperator.utils.Extrafields;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Slf4j
@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepositoty personRepositoty;
    private ApplicationProperties properties;

    @Override
    public Single<PersonData> findPersonById(String personId, List<String> extraFields) {
        return searchPersonById(personId, extraFields);
    }

    private Single<PersonData> searchPersonById(String personId, List<String> extraFields) {
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
