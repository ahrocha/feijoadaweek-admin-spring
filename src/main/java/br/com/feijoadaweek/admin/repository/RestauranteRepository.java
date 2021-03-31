package br.com.feijoadaweek.admin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.feijoadaweek.admin.model.Prato;
import br.com.feijoadaweek.admin.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer>{

	List<Restaurante> findBySlug(String slug);

//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	public List<Restaurante> findAll() {
//		Query query = entityManager.createQuery("SELECT r FROM Restaurante r ORDER BY r.nome DESC", Restaurante.class);
//		
//		return query.getResultList();
//	}
//
//	public Restaurante findById(int id) {
//		Restaurante restaurante = entityManager.find(Restaurante.class, id);
//		
//		return restaurante;
//	}
//
//	public List<Restaurante> findBySlug(String slug) {		
//		Query query = entityManager.createQuery("SELECT r FROM Restaurante r WHERE r.slug = :slug order by r.data DESC", Restaurante.class);
//		query.setParameter("slug", slug);
//		
//		return query.getResultList();
//	}
}
