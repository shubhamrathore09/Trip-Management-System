package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer>{

}
