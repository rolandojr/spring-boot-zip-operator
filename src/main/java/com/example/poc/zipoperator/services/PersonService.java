package com.example.poc.zipoperator.services;

import com.example.poc.zipoperator.model.PersonData;
import io.reactivex.Single;

import java.util.List;

public interface PersonService {

    Single<PersonData> findPersonById(String personId, List<String> extrafields);
}
