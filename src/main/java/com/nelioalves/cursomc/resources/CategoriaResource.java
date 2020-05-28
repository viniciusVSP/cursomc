package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.services.CategoriaService;

import antlr.collections.List;
import javassist.tools.rmi.ObjectNotFoundException;


@RestController
@RequestMapping(value="/categories")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	
	@RequestMapping(value="/(id)",method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id]").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id})", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List> findAll() {
		List list = service.findAll();
		List listDto = ((Collection<Categoria>) list).stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}

}
