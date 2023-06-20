package com.grupoacert.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoacert.domain.Entrega;
import com.grupoacert.domain.Pedido;
import com.grupoacert.dto.PedidoDTO;
import com.grupoacert.repositories.PedidoRepository;
import com.grupoacert.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	private PedidoRepository pedidoRepository;
	private ClienteService clienteService;
	
	@Autowired
	public PedidoService(PedidoRepository pedidoRepository, ClienteService clienteService) {
		this.pedidoRepository = pedidoRepository;
		this.clienteService = clienteService;
	}
	
	@Transactional
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
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
		Pedido novoPedido = this.find(pedido.getId());
		novoPedido.setCliente(pedido.getCliente());
		return pedidoRepository.saveAndFlush(novoPedido);
	}

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	public Pedido findByIdEntrega(Integer idEntrega) {
		return pedidoRepository.findByEntregaId(idEntrega);
	}
	
	public Pedido fromDTO(PedidoDTO pedidoDTO) {
		return new Pedido(null, clienteService.find(pedidoDTO.getIdCliente()), null);
	}

}