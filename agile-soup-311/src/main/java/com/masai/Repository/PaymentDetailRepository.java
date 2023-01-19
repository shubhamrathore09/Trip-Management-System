package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.PaymentDetail;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Integer>{

}
