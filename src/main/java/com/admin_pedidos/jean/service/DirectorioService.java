package com.admin_pedidos.jean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin_pedidos.jean.entity.Directorio;
import com.admin_pedidos.jean.repository.DirectorioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorioService {

    @Autowired
    private DirectorioRepository directorioRepository;

    public List<Directorio> findAll() {
        return directorioRepository.findAll();
    }

    public Optional<Directorio> findById(String id) {
        return directorioRepository.findById(id);
    }

    public boolean existsByNitdir(String nitdir) {
        return directorioRepository.existsByNitdir(nitdir);
    }

    public List<Directorio> findByVddor(String vddor) {
        return directorioRepository.findByVddor(vddor);
    }

    public List<Directorio> findByClte(String clte) {
        return directorioRepository.findByClte(clte);
    }

    public Directorio save(Directorio directorio) {
        return directorioRepository.save(directorio);
    }

    public Directorio update(String id, Directorio directorio) {
        if (directorioRepository.existsById(id)) {
            directorio.setNitdir(id);
            return directorioRepository.save(directorio);
        }
        throw new RuntimeException("Directorio not found");
    }

    public void deleteById(String id) {
        directorioRepository.deleteById(id);
    }
    public Directorio findByNomdir(String nomdir) {
        List<Directorio> directorio = directorioRepository.findByNomdir(nomdir);
        if (!directorio.isEmpty()) {
            for (Directorio dir : directorio) {
                int nit = convertirAEntero(dir.getNitdir());
                if ( nit >= 9999) {
                    return dir;
                 }
            }
            return directorio.get(0);
        }else{
            return null;
        } 
    }
    public static int convertirAEntero(String numeroComoString) {
        try {
            return Integer.parseInt(numeroComoString);
        } catch (NumberFormatException e) {
            return 0; // Valor predeterminado si ocurre una excepción
        }
    }
    
}