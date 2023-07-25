package io.example.DAO;

import io.example.domain.DomainObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainObjectDAO extends JpaRepository<DomainObject, Long> {

    DomainObject findByAnyAttribute(String anyAttribute);

}