package com.admin_pedidos.jean.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin_pedidos.jean.entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, String> {
    boolean existsByCodmar(String codmar);
}


