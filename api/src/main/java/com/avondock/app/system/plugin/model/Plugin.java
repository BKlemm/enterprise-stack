package com.avondock.app.system.plugin.model;

import javax.persistence.*;

@Table(name = "plugin", schema = "system")
@Entity
public class Plugin {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
}
