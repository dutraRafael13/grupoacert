package com.grupoacert.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoacert.domain.Entrega;
import com.grupoacert.domain.Pedido;
import com.grupoacert.dto.EntregaDTO;
import com.grupoacert.repositories.EntregaRepository;
import com.grupoacert.services.exceptions.DuplicateDeliveryException;
import com.grupoacert.services.exceptions.ObjectNotFoundException;

@Service
public class EntregaService {

	private EntregaRepository entregaRepository;
	private PedidoService pedidoService;
	
	@Autowired
	public EntregaService(EntregaRepository entregaRepository, PedidoService pedidoService) {
		this.entregaRepository = entregaRepository;
		this.pedidoService = pedidoService;
	}
	
	@Transactional
	public Entrega insert(Entrega entrega) {
		this.verificaPedido(entrega.getPedido().getId());
		entrega.setId(null);
		entrega = entregaRepository.save(entrega);
		entrega.getPedido().setEntrega(entrega);
		pedidoService.update(entrega.getPedido());
		return entrega;
	}
	
	private void verificaPedido(Integer idPedido) {
		Pedido pedido = pedidoService.find(idPedido);
		if (pedido != null && pedido.getEntrega() != null) {
			throw new DuplicateDeliveryException("Pedido já entregue, ID: " + pedido.getId());
		}
	}
	
	public Entrega update(Entrega entrega) {
		entrega = entregaRepository.saveAndFlush(entrega);
		entrega.getPedido().setEntrega(entrega);
		this.updatePedido(entrega.getId());
		pedidoService.update(entrega.getPedido());
		return entregaRepository.saveAndFlush(entrega);
	}
	
	private void updatePedido(Integer idEntrega) {
		Pedido pedido = pedidoService.findByIdEntrega(idEntrega);
		if (pedido != null) {
			pedido.setEntrega(null);
			pedidoService.update(pedido);
		}
	}
	
	public void delete(Integer id) {
		Optional<Entrega> entrega = entregaRepository.findById(id);
		if (!entrega.isPresent()) {
			throw new ObjectNotFoundException("Entrega não encontrada, ID: " + id);
		}
		entregaRepository.deleteById(entrega.get().getId());
	}
	
	public Entrega find(Integer id) {
		Optional<Entrega> entrega = entregaRepository.findById(id);
		return entrega.orElseThrow(() -> new ObjectNotFoundException("Entrega não encontrada, ID: " + id));
	}

	public List<Entrega> findAll() {
		List<Entrega> entregas = entregaRepository.findAll();
		return entregas;
	}
	
	public Entrega fromDTO(EntregaDTO entregaDTO) {
		return new Entrega(null, pedidoService.find(entregaDTO.getIdPedido()));
	}
	
}