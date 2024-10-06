package com.saurabhshcs.examinism.controller;

import com.saurabhshcs.examinism.domain.Question;
import com.saurabhshcs.examinism.exception.QuestionException;
import com.saurabhshcs.examinism.service.QuestionService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/questions")
@Slf4j
public class QuestionController {

    private static final String NO_QUESTION_FOUND_FOR_ID = "No Question found for id: ";

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("complexity/{complexity}")
    public List<Question> getAllQuestionsByComplexity(@PathVariable final Integer complexity){
        log.info("Complexity: {}", complexity);
        return questionService.getQuestionsByComplexity(complexity);
    }

    @PutMapping("/insert")
    public ResponseEntity<HttpStatus> insertQuestion(final @RequestBody Question question){

        questionService.insertQuestion(question);

        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public Question getQuestion(final @PathVariable String id){
        
        Optional<Question> question = questionService.getQuestionById(id);

        if(question.isPresent()){
            question.get();
        }
         
        return question.orElseThrow(() -> new QuestionException(NO_QUESTION_FOUND_FOR_ID + id));
    }
}
