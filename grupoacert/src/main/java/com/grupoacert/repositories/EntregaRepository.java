package com.grupoacert.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupoacert.domain.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

}
