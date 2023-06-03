package com.klass.lab01.repository;

import com.klass.lab01.domain.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepository extends JpaRepository<Logger,Long> {
}
