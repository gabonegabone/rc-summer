package ru.redcollar.summer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.redcollar.summer.entity.Talk;

public interface TalkRepository extends JpaRepository<Talk, Long> {
}
