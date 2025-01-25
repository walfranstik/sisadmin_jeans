package com.admin_pedidos.jean.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.admin_pedidos.jean.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query("SELECT p FROM Producto p WHERE " +
           "LOWER(p.ref) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.cole) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.descref) LIKE LOWER(CONCAT('%',:keyword, '%')) " +
           "ORDER BY p.id_pdto DESC")
    Page<Producto> search(@Param("keyword") String keyword, Pageable pageable);
}


