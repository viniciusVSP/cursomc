package com.nelioalves.cursomc.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Categoria;

@Repository
public interface ItemPedidoRepository extends JpaRepository<Categoria, Integer>{

	void save(List<Categoria> asList);

	Categoria findOne(Integer id);

	

}
