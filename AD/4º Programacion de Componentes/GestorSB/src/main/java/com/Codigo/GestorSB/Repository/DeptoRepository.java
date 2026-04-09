package com.Codigo.GestorSB.Repository;

import com.Codigo.GestorSB.Entity.Depto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface DeptoRepository extends JpaRepository<Depto, BigDecimal> {
}