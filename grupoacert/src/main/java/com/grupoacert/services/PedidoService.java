package com.grupoacert.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoacert.domain.Pedido;
import com.grupoacert.repositories.PedidoRepository;
import com.grupoacert.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		return pedidoRepository.save(pedido);
	}
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public void delete(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (!pedido.isPresent()) {
			throw new ObjectNotFoundException("Pedido não encontrado, ID: " + id);
		}
		pedidoRepository.deleteById(pedido.get().getId());
	}
	
	public Pedido update(Pedido pedido) {
		return pedidoRepository.saveAndFlush(pedido);
	}

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

}
