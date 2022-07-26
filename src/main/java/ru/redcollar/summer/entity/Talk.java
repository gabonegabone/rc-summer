package ru.redcollar.summer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Getter
@Setter
@Entity
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;

    @ManyToMany(mappedBy = "signedTalks", fetch = FetchType.LAZY)
    private Set<Guest> guests;

    @Override
    public String toString() {
        return id + ", " + name;
    }
}
