package br.com.feijoadaweek.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.feijoadaweek.admin.model.Cerveja;
import br.com.feijoadaweek.admin.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer>{

	public List<Marca> findTop100ByOrderByIdDesc();

}
