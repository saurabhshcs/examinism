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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

    private static final String NO_QUESTION_FOUND_FOR_ID_123 = "No Question found for id: 123";

    private static final String TEST_QUESTION_1 = "What is Java?";

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
        assertEquals(TEST_QUESTION_1, result.get(0).getDescription());
        verify(questionService, times(1)).getAllQuestions();
    }

@Test
void testInsertQuestion_Success(){
    // Mock the insertQuestion method to return the question object when called
    when(questionService.insertQuestion(any(Question.class))).thenReturn(question);

    // Call the insertQuestion method
    ResponseEntity<HttpStatus> response = questionController.insertQuestion(question);

    // Assert that the response status is HttpStatus.CREATED
    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    // Verify that the service's insertQuestion method was called exactly once
    verify(questionService, times(1)).insertQuestion(question);
}


    // Negative Scenario: Test getting a question by ID when the question is not found
    @Test
    void testGetQuestionById_NotFound(){
        String questionId = "123";
        when(questionService.getQuestionById(questionId)).thenReturn(Optional.empty());

        try {
            questionController.getQuestion(questionId);
        } catch (QuestionException ex) {
            assertEquals(NO_QUESTION_FOUND_FOR_ID_123, ex.getMessage());
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
                .description(TEST_QUESTION_1)
                .complexity(0)
                .build();
    }
}
