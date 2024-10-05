package com.saurabhshcs.examinism.repository.impl;

import com.saurabhshcs.examinism.domain.Question;
import com.saurabhshcs.examinism.repository.QuestionRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public abstract class QuestionRepositoryImpl implements QuestionRepository {
    @Override
    public <S extends Question> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Question> List<S> insert(Iterable<S> entities) {
        return Collections.emptyList();
    }

    @Override
    public <S extends Question> List<S> findAll(Example<S> example) {
        return Collections.emptyList();
    }

    @Override
    public <S extends Question> List<S> findAll(Example<S> example, Sort sort) {
        return Collections.emptyList();
    }
}
