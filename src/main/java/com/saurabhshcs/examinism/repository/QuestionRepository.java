package com.saurabhshcs.examinism.repository;

import com.saurabhshcs.examinism.domain.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, String> {

    @Query
    public List<Question> findByComplexity(final Integer complexity);
}
