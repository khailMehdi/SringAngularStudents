package org.example.angularspring.service;

import jakarta.transaction.Transactional;
import org.example.angularspring.entities.Payment;
import org.example.angularspring.entities.PaymentStatus;
import org.example.angularspring.entities.PaymentType;
import org.example.angularspring.entities.Student;
import org.example.angularspring.repository.PaymentRepository;
import org.example.angularspring.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    public PaymentService(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }
    public Payment savePayment(MultipartFile file, double amont, PaymentType type, String studentCode)
            throws IOException {
        Path folderPath= Paths.get(System.getProperty("user.home"),"data","payments");
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath =Paths.get(System.getProperty("user.home"),"data","payments",fileName+".pdf");
        System.out.println("filePath->"+filePath);
        Files.copy(file.getInputStream(),filePath);
        Student student =studentRepository.findByCode(studentCode);
        Payment payment= Payment.builder()
                //.date(date)
                .type(type).student(student).amount(amont)
                .file(filePath.toUri().toString())
                .status(PaymentStatus.CREATED)
                .build();
        return paymentRepository.save(payment);

    }
    public byte[]getPaymentFile( Long paymentId) throws IOException {
        Payment payment=paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }


    public Payment updatPaymentStatus( PaymentStatus status, Long id){
        Payment payment=paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }
}
