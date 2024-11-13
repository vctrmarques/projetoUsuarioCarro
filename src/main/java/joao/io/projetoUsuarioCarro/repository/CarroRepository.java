package joao.io.projetoUsuarioCarro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import joao.io.projetoUsuarioCarro.entity.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer>{
	
	 List<Carro> findAll();
	 public Optional<Carro> findById(@Param("id") Integer id);

}
