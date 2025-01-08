package com.admin_pedidos.jean.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin_pedidos.jean.entity.Directorio;

@Repository
public interface DirectorioRepository extends JpaRepository<Directorio, String> {
}