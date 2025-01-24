package com.admin_pedidos.jean.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.admin_pedidos.jean.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long > {

    @Query("SELECT p FROM Pedido p WHERE " +
           "LOWER(p.vddor) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.clte) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.numped) LIKE LOWER(CONCAT(:keyword)) OR " +
            "CAST(p.fechaped AS string) LIKE CONCAT('%', :keyword, '%') " +
           "ORDER BY p.fechaped DESC")
    Page<Pedido> search(@Param("keyword") String keyword, Pageable pageable);
    
}


