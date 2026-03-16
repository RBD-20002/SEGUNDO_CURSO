package com.codigo.Empresa.Repositorios;

import com.codigo.Empresa.Entidades.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryEmp extends JpaRepository<Emp, Long> {

    @Query("select e from Emp e where (e.nomemp like :letra and e.sal > :salario) or (e.comision != null and e.deptoJefe.numdep = :idDep)")
    List<Emp> nombreEmpleadoLetraSalariooComicionDepartamento(@Param("letra") String letra,
                                                              @Param("salario") double salario,
                                                              @Param("idDep") Long idDep);

    @Query("select e from Emp e where e.puesto != :puesto")
    List<Emp> nombreyFechaEmpleadosNoPuesto(@Param("puesto") String puesto);

    List<Emp> findByComisionIsNotNull();

    @Query("select e from Emp e where e.sal > (select e1.sal from Emp e1 where e1.numemp=:id) order by sal")
    List<Emp> empleadosSalarioMayorASalariodeOtroCompanhero(@Param("id") Long id);
}
