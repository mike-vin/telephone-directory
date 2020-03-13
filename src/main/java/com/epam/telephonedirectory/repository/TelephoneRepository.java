package com.epam.telephonedirectory.repository;

import com.epam.telephonedirectory.domain.Telephone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepository extends CrudRepository<Telephone, Long> {
}
