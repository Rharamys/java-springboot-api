package io.example.DAO;

import io.example.domain.DomainExampleObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainExampleObjectDAO extends JpaRepository<DomainExampleObject, Long> {

    DomainExampleObject findByAnyAttribute(String anyAttribute);

}