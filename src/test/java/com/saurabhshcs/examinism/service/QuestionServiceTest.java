package com.saurabhshcs.examinism.service;

import com.saurabhshcs.examinism.domain.Question;
import com.saurabhshcs.examinism.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class QuestionServiceTest {

    private static final String TEST_INSERT_QUESTION_1 = "What is Java?";

    private static final Integer TEST_COMPLEXITY_2 = 2;

    private static final String TEST_QUESTION_2 = "What is Docker";

    private static final Integer TEST_COMPLEXITY_1 = 2;

    private static final String TEST_QUSTION_1 = "What is JavaScript";

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    private Question question;

    @BeforeEach
    void setUp(){
        question = questionMaker();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllQuestions(){
        List<Question> questions = new ArrayList<>();
        questions.add(Question
                            .builder()
                            .description(TEST_QUSTION_1)
                            .complexity(TEST_COMPLEXITY_1)
                            .build());
        questions.add(Question
                            .builder()
                            .description(TEST_QUESTION_2)
                            .complexity(TEST_COMPLEXITY_2)
                            .build());
        
        when(questionRepository.findAll()).thenReturn(questions);
        
        List<Question> result = questionService.getAllQuestions();
        
        assertEquals(TEST_QUSTION_1, result.stream()
                                                 .map(Question::getDescription)
                                                 .findFirst().get());
        assertEquals(TEST_COMPLEXITY_1, result.stream()
                              .map(Question::getComplexity)
                              .findFirst().get());
        
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    void testInsertQuestion(){
        when(questionRepository.save(any(Question.class))).thenReturn(question);

        Question result = questionService.insertQuestion(question);

        assertEquals(TEST_INSERT_QUESTION_1, result.getDescription());
        assertEquals(TEST_COMPLEXITY_1, result.getComplexity());
        
        verify(questionRepository, times(1)).save(question);
    }

    private Question questionMaker(){
        return Question.builder()
                       .description(TEST_INSERT_QUESTION_1)
                       .complexity(TEST_COMPLEXITY_1)
                       .build();
    }
}