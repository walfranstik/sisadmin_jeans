package com.admin_pedidos.jean.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin_pedidos.jean.entity.TipoPrenda;
@Repository
public interface TipoPrendaRepository extends JpaRepository<TipoPrenda, String> {
    boolean existsByCodpre(String codpre);
}
