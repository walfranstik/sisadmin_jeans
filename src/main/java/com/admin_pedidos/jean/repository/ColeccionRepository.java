package com.admin_pedidos.jean.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin_pedidos.jean.entity.Coleccion;


@Repository
public interface ColeccionRepository extends JpaRepository<Coleccion, String> {
    boolean existsByCodcole(String codcole);
}