package com.dev.race;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "prefixes")
public class TireMixturePrefixes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;
    @Column
    int Hot, Medium, Cold, Intermediate, Dry_wet, Heavy_wet;
    @OneToOne(mappedBy = "prefixes")
    @JoinColumn(name = "raceID")
    @JsonIgnore
    Race race;


    public TireMixturePrefixes(int hot, int medium, int cold, int intermediate, int dry_wet, int heavy_wet) {
        Hot = hot;
        Medium = medium;
        Cold = cold;
        Intermediate = intermediate;
        Dry_wet = dry_wet;
        Heavy_wet = heavy_wet;
    }

    public TireMixturePrefixes() {

    }

    public int getprefix(String pref) {
        switch (pref) {
            case "Hot" -> {
                return Hot;
            }
            case "Medium" -> {
                return Medium;
            }
            case "Cold" -> {
                return Cold;
            }
            case "Dry_wet" -> {
                return Dry_wet;
            }
            case "Heavy_wet" -> {
                return Heavy_wet;
            }
            case "Intermediate" -> {
                return Intermediate;
            }
        }
        throw new IllegalStateException(String.format("%s must be one of [Hot, Medium, Cold, Intermediate, Dry_wet, Heavy_wet]", pref));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int[] getPrefs() {
        return new int[]{Hot, Medium, Cold, Intermediate, Dry_wet, Heavy_wet};
    }

    public void setPrefs(int[] prefs) {
        Hot = prefs[0];
        Medium = prefs[1];
        Cold = prefs[2];
        Intermediate = prefs[3];
        Dry_wet = prefs[4];
        Heavy_wet = prefs[5];
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}