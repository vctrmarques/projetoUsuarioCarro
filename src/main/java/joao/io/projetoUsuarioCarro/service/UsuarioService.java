package joao.io.projetoUsuarioCarro.service;

import java.util.List;

import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import joao.io.projetoUsuarioCarro.entity.Usuario;
import joao.io.projetoUsuarioCarro.exception.UsuarioException;


@Service
public interface UsuarioService {
	
	public List<Usuario> buscarUsuario();
    public Usuario salvarUsuario(Usuario usuario) throws UsuarioException;
    public void removeUsuario(int id) throws NoSuchMessageException, UsuarioException;
	public Usuario getById(Integer id) throws UsuarioException;
	public Usuario updateUsuario(Usuario user, Integer id) throws NoSuchMessageException, UsuarioException;

}
