package fr.stazi.epsi.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stazi.epsi.spring.boot.entity.Cell;

public interface cellRepository extends JpaRepository<Cell, Long>{

}
