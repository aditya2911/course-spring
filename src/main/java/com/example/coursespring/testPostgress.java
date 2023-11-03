package com.example.coursespring;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table()
public class testPostgress {
    @Id
    private Long id;

    @Column
    private String name;

    @Column String number;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
