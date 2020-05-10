package com.mt.springbootmongo.repository;

import com.mt.springbootmongo.domain.UserSequences;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSequencesRepository extends MongoRepository<UserSequences, String> {
}
