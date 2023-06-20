package com.grupoacert.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.grupoacert.domain.Cliente;
import com.grupoacert.repositories.ClienteRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder enconde;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void instantiateTestDatabase() {
		Cliente cliente = new Cliente(null, "Supervisor", "supervisor@gmail.com", enconde.encode("123"), new ArrayList<>());
		clienteRepository.save(cliente);
	}

}
