package org.example.angularspring.web;

import org.example.angularspring.entities.Payment;
import org.example.angularspring.entities.PaymentStatus;
import org.example.angularspring.entities.PaymentType;
import org.example.angularspring.entities.Student;
import org.example.angularspring.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.example.angularspring.repository.PaymentRepository;
import org.example.angularspring.repository.StudentRepository;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
 public PaymentService paymentService;
    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping(path = "/payments")
    public List<Payment> allPayment() {
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/students")
    public List<Student> allStudent() {
        return studentRepository.findAll();
    }


    @GetMapping(path = "/students/{code}/payments/")
    public List<Payment> paymentsByStudent(@PathVariable String code) {
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path = "payments/")
    public List<Payment> paymentsByStatus(@RequestParam PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @GetMapping(path = "payments/byType")
    public List<Payment> paymentsByType(@RequestParam PaymentType type) {
        return paymentRepository.findByType(type);
    }

    @GetMapping(path = "/payments/byId/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id).get();
    }

    @GetMapping(path = "/students/{id}")
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{code}")
    public Student getStudentByCode(String code) {
        return studentRepository.findByCode(code);
    }

    @GetMapping("/studentsByProgramId/")
    public List<Student> getStudentsByProgramId(@RequestParam String programId) {
        return studentRepository.findByProgramId(programId);
    }
@PutMapping("/payments/{id}")
    public Payment updatPaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id){
//        Payment payment=paymentRepository.findById(id).get();
//        payment.setStatus(status);
        return this.paymentService.updatPaymentStatus(status,id);
    }
@PostMapping(path="/payment",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file,double amount,PaymentType type,String studentCode)
        throws IOException {
        return this.paymentService.savePayment(file,amount,type,studentCode);
    }


    @GetMapping(path = "/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[]getPaymentFile(@PathVariable Long paymentId) throws IOException {

       return this.paymentService.getPaymentFile(paymentId);
    }
}
