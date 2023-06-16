package com.grupoacert.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoacert.domain.Cliente;
import com.grupoacert.repositories.ClienteRepository;
import com.grupoacert.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return clienteRepository.save(cliente);
	}
	
	public Cliente update(Cliente cliente) {
		return clienteRepository.saveAndFlush(cliente);
	}
	
	public void delete(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent()) {
			throw new ObjectNotFoundException("Cliente não encontrado, ID: " + id);
		}
		clienteRepository.deleteById(cliente.get().getId());
	}
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado, ID: " + id));
	}

}
