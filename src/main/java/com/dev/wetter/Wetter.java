package com.dev.wetter;

import com.dev.rennen.Rennen;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "wetter")
public class Wetter {
    @Id
    @GeneratedValue
    Long WetterID;
    @Column(nullable = false)
    Time uhrzeit;
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "RennID")
    Rennen rennen;
}
