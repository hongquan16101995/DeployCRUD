package com.example.quan.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phone;
    private String image;

    @Transient
    private MultipartFile avatar;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
