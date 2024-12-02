package com.Mohit.QuizApp.Service;

import com.Mohit.QuizApp.DAO.QuestionDoa;
import com.Mohit.QuizApp.DAO.QuizDao;
import com.Mohit.QuizApp.Entity.Question;
import com.Mohit.QuizApp.Entity.QuestionWrapper;
import com.Mohit.QuizApp.Entity.Quiz;
import com.Mohit.QuizApp.Entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDoa questionDoa;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions=questionDoa.findRandomQuestionByCategory(category);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.findById(id).orElse(null);
        List<Question> quizQuestionsFromDb = quiz.getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
         for( Question q: quizQuestionsFromDb){
             QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
             questionForUser.add(qw);
         }
         return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).orElse(null);
        List<Question> quizQuestions = quiz.getQuestions();
         int right=0;
         int i=0;
        for(Response response: responses){
            if(response.getResponse().equals(quizQuestions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
