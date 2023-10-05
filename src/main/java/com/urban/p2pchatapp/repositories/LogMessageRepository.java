package com.urban.p2pchatapp.repositories;

import com.urban.p2pchatapp.models.LogMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMessageRepository extends JpaRepository<LogMessage, Long> {
}
