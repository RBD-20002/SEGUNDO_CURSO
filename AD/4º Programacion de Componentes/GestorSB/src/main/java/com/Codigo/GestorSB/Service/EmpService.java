package com.Codigo.GestorSB.Service;

import com.Codigo.GestorSB.DTO.EmpDTO;
import com.Codigo.GestorSB.Entity.Emp;
import com.Codigo.GestorSB.Repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpService {

    @Autowired
    public EmpRepository empRepository;

    public List<EmpDTO> filtrarEmpleadosSegunSalario(BigDecimal salario) throws Exception{
        List<Emp> empleados = empRepository.listEmpMaxSal(salario);
        List<EmpDTO> filtro = new ArrayList<>();

        for(Emp emp : empleados){
            EmpDTO empDTO = new EmpDTO(emp.getNomemp(), emp.getSal());
            filtro.add(empDTO);
        }
        return filtro;
    }

    public int eliminarEmpleadoPorPuesto(String puesto) throws Exception{
        return empRepository.deleteEmpForPuesto(puesto);
    }
}
