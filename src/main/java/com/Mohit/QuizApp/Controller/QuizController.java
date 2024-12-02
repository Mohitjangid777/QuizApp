package com.Mohit.QuizApp.Controller;

import com.Mohit.QuizApp.Entity.Question;
import com.Mohit.QuizApp.Entity.QuestionWrapper;
import com.Mohit.QuizApp.Entity.Response;
import com.Mohit.QuizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

     @PostMapping( "create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

     @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
      return  quizService.getQuizQuestions(id);
    }

    @PostMapping("sumbit/{id}")
    public ResponseEntity<Integer> sumbitQuiz(@PathVariable Integer id,@RequestBody List<Response> response){
     return  quizService.calculateResult(id,response);
    }
}
