package ru.redcollar.summer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long age;
    private Boolean isStudent;
    private String jobName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "guest_talk",
            joinColumns = @JoinColumn(name = "talk_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id"))
    private Set<Talk> signedTalks;

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", name, age, isStudent, jobName);
    }
}
