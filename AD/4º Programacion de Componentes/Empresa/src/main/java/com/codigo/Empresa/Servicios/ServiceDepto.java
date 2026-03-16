package com.codigo.Empresa.Servicios;

import com.codigo.Empresa.Entidades.Depto;
import com.codigo.Empresa.Repositorios.RepositoryDepto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDepto {

    private final RepositoryDepto repositoryDepto;

    public ServiceDepto(RepositoryDepto repositoryDepto) {
        this.repositoryDepto = repositoryDepto;
    }

    public void guardar(Depto depto) {
        repositoryDepto.save(depto);
    }

    public Depto addNewDepto(Depto depto){
        return repositoryDepto.save(depto);
    }

    public Depto remove(Long id){
        Depto depto = repositoryDepto.findById(id).orElse(null);
        repositoryDepto.deleteById(id);
        return depto;
    }

    public Depto update(Depto depto){
        return repositoryDepto.save(depto);
    }

    public List<Depto> findAll(){
        return repositoryDepto.findAll();
    }

    public Depto findById(Long id) {
        return repositoryDepto.findById(id).orElse(null);
    }

    public List<Depto> findByNomdepIsNotAndNomdepIsNot(String nomDep1, String nomDep2) {
        return repositoryDepto.findByNomdepIsNotAndNomdepIsNot(nomDep1, nomDep2);
    }
}
