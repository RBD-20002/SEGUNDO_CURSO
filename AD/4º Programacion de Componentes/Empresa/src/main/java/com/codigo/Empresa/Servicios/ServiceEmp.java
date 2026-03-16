package com.codigo.Empresa.Servicios;

import com.codigo.Empresa.Entidades.Emp;
import com.codigo.Empresa.Repositorios.RepositoryEmp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceEmp {

    private final RepositoryEmp repositoryEmp;

    public ServiceEmp(RepositoryEmp repositoryEmp) {
        this.repositoryEmp = repositoryEmp;
    }

    public void guardar(Emp emp) {
        repositoryEmp.save(emp);
    }

    public Emp addNewDepto(Emp emp){
        return repositoryEmp.save(emp);
    }

    public Emp remove(Long id){
        Emp emp = repositoryEmp.findById(id).orElse(null);
        repositoryEmp.deleteById(id);
        return emp;
    }

    public Emp update(Emp emp){
        return repositoryEmp.save(emp);
    }

    public List<Emp> findAll(){
        return repositoryEmp.findAll();
    }

    public Emp findById(Long id) {
        return repositoryEmp.findById(id).orElse(null);
    }

    public List<Emp> nombreyFechaEmpleadosNoPuesto(String puesto) {
        return repositoryEmp.nombreyFechaEmpleadosNoPuesto(puesto);
    }

    public List<String> findByCominionNotNull() {
        return repositoryEmp.findByComisionIsNotNull().stream().map(Emp::getNomemp).collect(Collectors.toList());
    }

    public List<Emp> empleadosSalarioMayorASalariodeOtroCompanhero(Long id) {
        return repositoryEmp.empleadosSalarioMayorASalariodeOtroCompanhero(id);
    }
}
