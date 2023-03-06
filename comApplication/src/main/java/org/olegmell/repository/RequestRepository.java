package org.olegmell.repository;

import org.olegmell.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByTag(String tag);


}
