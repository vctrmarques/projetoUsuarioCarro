package joao.io.projetoUsuarioCarro.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import joao.io.projetoUsuarioCarro.entity.Usuario;
import joao.io.projetoUsuarioCarro.exception.UsuarioException;
import joao.io.projetoUsuarioCarro.repository.CarroRepository;
import joao.io.projetoUsuarioCarro.repository.UsuarioRepository;

@Service("userService")
public class UserServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CarroRepository carroRepository;
	
	@Autowired
    private MessageSource messageSource;
	
	public List<Usuario> buscarUsuario(){
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario getById(Integer id) throws UsuarioException{
		try {
			Optional<Usuario> usuario = usuarioRepository.findById(id);
			if (usuario.isPresent()) {
				return usuario.get();
			}else {
				throw new UsuarioException(String.format(messageSource.getMessage("Usuario.erro.Usuario.naoencontrado", null, Locale.getDefault()), id), null);
			}
		}catch(UsuarioException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new UsuarioException(String.format(messageSource.getMessage("Usuario.erro.consulta", null, Locale.getDefault()), id),e);
		}
	}
	
	@Override
	public Usuario salvarUsuario(Usuario usuario) throws UsuarioException{
		try {
			carroRepository.saveAll(usuario.getCars());
			usuarioRepository.save(usuario);
			
			return usuarioRepository.save(usuario);
		}catch(Exception e) {
			e.printStackTrace();
			throw new UsuarioException(String.format(messageSource.getMessage("Usuario.erro.salvamento", null, Locale.getDefault()), usuario.getNome()),e);
		}
	}

	@Override
	public void removeUsuario(int id) throws NoSuchMessageException, UsuarioException {
		try {
			Optional<Usuario> usuario = usuarioRepository.findById(id);
			if (usuario.isPresent()) {
				usuarioRepository.delete(usuario.get());
			}else {
				throw new UsuarioException(String.format(messageSource.getMessage("Usuario.erro.Usuario.naoencontrado", null, Locale.getDefault()), id), null);
			}
		}catch(UsuarioException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new UsuarioException(String.format(messageSource.getMessage("Usuario.erro.remocao", null, Locale.getDefault()), id),e);
		}
	}

	@Override
	public Usuario updateUsuario(Usuario usuario, Integer id) throws NoSuchMessageException, UsuarioException {
		Optional<Usuario> usuarioOld = usuarioRepository.findById(id);
		usuarioOld.get().setBirthday(usuario.getBirthday());
		usuarioOld.get().setNome(usuario.getNome());
		usuarioOld.get().setFirstName(usuario.getFirstName());
		usuarioOld.get().setLastName(usuario.getLastName());
		usuarioOld.get().setEmail(usuario.getEmail());
		usuarioOld.get().setBirthday(usuario.getBirthday());
		usuarioOld.get().setLogin(usuario.getLogin());
		usuarioOld.get().setPassword(usuario.getPassword());
		usuarioOld.get().setPhone(usuario.getPhone());
		
		try {
			return usuarioRepository.save(usuarioOld.get());
		}catch(Exception e) {
			e.printStackTrace();
			throw new UsuarioException(String.format(messageSource.getMessage("Usuario.erro.atualização", null, Locale.getDefault()), usuario.getNome()),e);
		}
	}

}