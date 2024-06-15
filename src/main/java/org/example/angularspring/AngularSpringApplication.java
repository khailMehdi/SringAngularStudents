package org.example.angularspring;


import org.example.angularspring.entities.Payment;
import org.example.angularspring.entities.PaymentStatus;
import org.example.angularspring.entities.PaymentType;
import org.example.angularspring.entities.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.example.angularspring.repository.PaymentRepository;
import org.example.angularspring.repository.StudentRepository;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;
//@EntityScan(basePackages = {"org/example/angularspring/entities"})
@SpringBootApplication
public class AngularSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularSpringApplication.class, args);
	}


@Bean
	public CommandLineRunner commandLineRunner(StudentRepository studentRepository ,
											   PaymentRepository paymentRepository){
		return args ->{
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
							.firstName("Mehamed").code("15525").programId("SDIA")
					.build());

			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Mehdi").code("155455").programId("SMP")
					.build());

			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Jilali").code("145525").programId("SDIA")
					.build());

			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Asmaa").code("157425").programId("GLSID")
					.build());
			PaymentType[] paymentTypes =PaymentType.values();
            studentRepository.findAll().forEach(st->{
				for (int i = 0; i <10 ; i++) {
					int index=2;
					Payment payment =Payment.builder().amount(1000+(int)(Math.random()+200))
							.type(paymentTypes[index])
							.status(PaymentStatus.CREATED)
							.date(LocalDate.now())
							.student(st)
							.build();
					paymentRepository.save(payment);

				}
			}
				);
		};
	}

}
