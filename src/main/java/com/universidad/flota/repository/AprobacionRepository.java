package com.universidad.flota.repository;

import com.universidad.flota.domain.Aprobacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AprobacionRepository extends JpaRepository<Aprobacion, Long> {
}
