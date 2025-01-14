package com.admin_pedidos.jean.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin_pedidos.jean.entity.Tallaje;
@Repository
public interface TallajeRepository extends JpaRepository<Tallaje, String> {
    boolean existsByCodtall(String codtall);
}


