package com.admin_pedidos.jean.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin_pedidos.jean.entity.Linea;

@Repository
public interface LineaRepository extends JpaRepository<Linea, String> {
    boolean existsByCodlin(String codlin);
}

