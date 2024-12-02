package com.Mohit.QuizApp.DAO;

import com.Mohit.QuizApp.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
