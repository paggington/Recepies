package com.example.recepies.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class RequestCoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean hot;
    private boolean ings;
    private String field;

    public RequestCoEntity() {
    }

    public RequestCoEntity(boolean hot, boolean ings, String field) {
        this.hot = hot;
        this.ings = ings;
        this.field = field;
    }
}
