package com.saurabhshcs.examinism.controller;

import com.saurabhshcs.examinism.domain.Question;
import com.saurabhshcs.examinism.exception.QuestionException;
import com.saurabhshcs.examinism.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionController questionController;

    private Question question;

    @BeforeEach
    void setUp(){
        question = questionMaker();
    }

    // Positive Scenario: Test fetching all questions
    @Test
    void testGetAllQuestions_Success(){
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        when(questionService.getAllQuestions()).thenReturn(questions);

        List<Question> result = questionController.getAllQuestions();

        assertEquals(1, result.size());
        assertEquals("What is Java?", result.get(0).getDescription());
        verify(questionService, times(1)).getAllQuestions();
    }

    // Positive Scenario: Test inserting a question successfully
    // @Test
    // void testInsertQuestion_Success(){
    //     doNothing().when(questionService).insertQuestion(any(Question.class));

    //     ResponseEntity<HttpStatus> response = questionController.insertQuestion(question);

    //     assertEquals(HttpStatus.CREATED, response.getStatusCode());
    //     verify(questionService, times(1)).insertQuestion(question);
    // }

    // Negative Scenario: Test getting a question by ID when the question is not found
    @Test
    void testGetQuestionById_NotFound(){
        String questionId = "123";
        when(questionService.getQuestionById(questionId)).thenReturn(Optional.empty());

        try {
            questionController.getQuestion(questionId);
        } catch (QuestionException ex) {
            assertEquals("No Question found for id: 123", ex.getMessage());
        }

        verify(questionService, times(1)).getQuestionById(questionId);
    }

    // Negative Scenario: Test fetching questions by complexity when none found
    @Test
    void testGetQuestionsByComplexity_NotFound(){
        Integer complexity = 5;
        when(questionService.getQuestionsByComplexity(complexity)).thenReturn(new ArrayList<>());

        List<Question> result = questionController.getAllQuestionsByComplexity(complexity);

        assertEquals(0, result.size());
        verify(questionService, times(1)).getQuestionsByComplexity(complexity);
    }

    private Question questionMaker(){
        return Question.builder()
                .description("What is Java?")
                .complexity(0)
                .build();
    }
}
