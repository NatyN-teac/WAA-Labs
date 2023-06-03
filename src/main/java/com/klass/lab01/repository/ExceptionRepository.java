package com.klass.lab01.repository;


import com.klass.lab01.domain.Exception;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionRepository extends JpaRepository<Exception,Long> {
}
