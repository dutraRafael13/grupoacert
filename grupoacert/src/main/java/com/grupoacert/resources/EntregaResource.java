package com.grupoacert.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.grupoacert.domain.Entrega;
import com.grupoacert.services.EntregaService;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaResource {
	
	@Autowired
	private EntregaService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Entrega> find(@PathVariable Integer id) {
		Entrega entrega = service.find(id);
		return ResponseEntity.ok().body(entrega);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Entrega entrega) {
		entrega = service.insert(entrega);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entrega.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Entrega entrega, @PathVariable Integer id) {
		entrega.setId(id);
		entrega = service.update(entrega);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Entrega>> findAll() {
		List<Entrega> entregas = service.findAll();
		return ResponseEntity.ok().body(entregas);
	}

}
