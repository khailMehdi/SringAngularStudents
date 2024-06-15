package org.example.angularspring.entities;



import jakarta.persistence.*;

import lombok.*;


import java.time.LocalDate;

@Entity @Table(name ="XPaiment")
@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
    private String file ;
    @ManyToOne
    private Student student;
}
