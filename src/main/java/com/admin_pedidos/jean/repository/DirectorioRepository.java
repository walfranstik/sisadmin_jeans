package com.admin_pedidos.jean.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin_pedidos.jean.entity.Directorio;

@Repository
public interface DirectorioRepository extends JpaRepository<Directorio, String> {
    boolean existsByNitdir(String nitdir);
    
    List<Directorio> findByVddor(String vddor);
    
    List<Directorio> findByClteOrderByNomdirAsc(String clte);
    List<Directorio> findByNomdir(String nomdir);


}