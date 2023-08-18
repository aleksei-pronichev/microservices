package com.epam.pronichev.resourceservice.repository;

import com.epam.pronichev.resourceservice.model.entities.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {
}
