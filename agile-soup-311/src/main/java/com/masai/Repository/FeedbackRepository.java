package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.Exception.FeedbackException;
import com.masai.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer>{

	
	@Query("select f from Feedback f")
	public List<Feedback> getAllFeedbacks() throws FeedbackException;
}
