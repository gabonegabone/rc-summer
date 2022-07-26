package ru.redcollar.summer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.redcollar.summer.entity.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long>,
        JpaSpecificationExecutor<Guest> {
}
