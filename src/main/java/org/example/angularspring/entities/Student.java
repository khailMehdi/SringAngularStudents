package org.example.angularspring.entities;


import jakarta.persistence.*;

import lombok.*;



@Entity @Table(name ="XStudent")
@AllArgsConstructor
@NoArgsConstructor
@Data @Builder

public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String code;
    private  String programId;
    private String photo;



}
