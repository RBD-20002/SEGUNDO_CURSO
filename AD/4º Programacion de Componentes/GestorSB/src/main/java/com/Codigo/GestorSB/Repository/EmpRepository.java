package com.Codigo.GestorSB.Repository;

import com.Codigo.GestorSB.DTO.EmpDTO;
import com.Codigo.GestorSB.Entity.Emp;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmpRepository extends JpaRepository<Emp, BigDecimal> {
    @Query("SELECT e FROM Emp e WHERE e.sal >:salario")
    List<Emp> listEmpMaxSal(@Param("salario") BigDecimal salario);

    @Transactional
    @Modifying
    @Query("DELETE FROM Emp e WHERE e.puesto =:puesto")
    int deleteEmpForPuesto(@Param("puesto") String puesto);
}
