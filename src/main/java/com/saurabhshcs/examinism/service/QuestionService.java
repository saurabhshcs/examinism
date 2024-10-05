package com.saurabhshcs.examinism.service;

import com.saurabhshcs.examinism.domain.Question;
import com.saurabhshcs.examinism.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question insertQuestion(Question question) {
    return questionRepository.save(question);
    }

    public List<Question> insertQuestions(List<Question> questions) {
        return questionRepository.saveAll(questions);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByComplexity(Integer complexity) {
        return questionRepository.findByComplexity(complexity);
    }

    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Optional<Question> getQuestionById(String id){
        return questionRepository.findById(id);
    }

}
