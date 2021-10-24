package com.avondock.app.system.config.model;

import javax.persistence.*;

@Table(name = "config", schema = "system")
@Entity
public class Config {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    String instance;

    String name;

    String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
