package com.grupoacert.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.grupoacert.domain.Cliente;
import com.grupoacert.dto.ClienteDTO;
import com.grupoacert.repositories.ClienteRepository;
import com.grupoacert.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	private BCryptPasswordEncoder encrypt;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository, BCryptPasswordEncoder encrypt) {
		this.clienteRepository = clienteRepository;
		this.encrypt = encrypt;
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente.setPassword(encrypt.encode(cliente.getPassword()));
		return clienteRepository.save(cliente);
	}
	
	public Cliente update(Cliente cliente) {
		cliente.setPassword(encrypt.encode(cliente.getPassword()));
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
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getPassword(), new ArrayList<>());
	}

}
