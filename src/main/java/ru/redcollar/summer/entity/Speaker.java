package ru.redcollar.summer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "speaker", fetch = FetchType.LAZY)
    private Set<Talk> talks;

    @Override
    public String toString() {
        return id + ", " + name;
    }
}
