package com.archiadmin.user.repository;

import com.archiadmin.user.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query("SELECT c FROM Contract c WHERE DATE(c.endDate) = :date")
    Optional<List<Contract>> findByEndDate(@Param("date") LocalDate date);
}
