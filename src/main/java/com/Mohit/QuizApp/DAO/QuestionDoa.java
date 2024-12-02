package com.Mohit.QuizApp.DAO;

import com.Mohit.QuizApp.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDoa extends JpaRepository<Question,Integer> {

   List<Question> findByCategory(String category);

   @Query(value = "SELECT*FROM question q WHERE q.category=:category ORDER BY RAND()",nativeQuery = true)
   List<Question> findRandomQuestionByCategory(String category);
}
