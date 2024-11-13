package joao.io.projetoUsuarioCarro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import joao.io.projetoUsuarioCarro.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	public List<Usuario> findAll();
	public Optional<Usuario> findById(@Param("id") Integer id);
}
