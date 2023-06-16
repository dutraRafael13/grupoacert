package com.grupoacert.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoacert.domain.Entrega;
import com.grupoacert.repositories.EntregaRepository;
import com.grupoacert.services.exceptions.ObjectNotFoundException;

@Service
public class EntregaService {

	@Autowired
	private EntregaRepository entregaRepository;
	
	public Entrega insert(Entrega entrega) {
		entrega.setId(null);
		return entregaRepository.save(entrega);
	}
	
	public Entrega update(Entrega entrega) {
		return entregaRepository.saveAndFlush(entrega);
	}
	
	public void delete(Integer id) {
		Optional<Entrega> cliente = entregaRepository.findById(id);
		if (!cliente.isPresent()) {
			throw new ObjectNotFoundException("Entrega não encontrada, ID: " + id);
		}
		entregaRepository.deleteById(cliente.get().getId());
	}
	
	public Entrega find(Integer id) {
		Optional<Entrega> cliente = entregaRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Entrega não encontrada, ID: " + id));
	}
	
}
