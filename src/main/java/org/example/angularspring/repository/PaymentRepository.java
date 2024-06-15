package org.example.angularspring.repository;

import org.example.angularspring.entities.Payment;
import org.example.angularspring.entities.PaymentStatus;
import org.example.angularspring.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStudentCode(String code);
List<Payment>findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);

}
