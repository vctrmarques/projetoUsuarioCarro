package joao.io.projetoUsuarioCarro.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import joao.io.projetoUsuarioCarro.entity.Usuario;
import joao.io.projetoUsuarioCarro.exception.UsuarioException;
import joao.io.projetoUsuarioCarro.service.UsuarioService;
import joao.io.projetoUsuarioCarro.util.Constantes;

@RestController
public class RestUsarioCarroController {

	@Autowired
	private UsuarioService userService;

	Logger logger = LoggerFactory.getLogger(RestUsarioCarroController.class);

	@RequestMapping(path = Constantes.Url.URL_SIGNIN, method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(userService.buscarUsuario());
	}

	@RequestMapping(path = Constantes.Url.URL_USERS, method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> getAllUser() {
		return ResponseEntity.ok(userService.buscarUsuario());
	}
	
	@RequestMapping(path = Constantes.Url.URL_USERS + "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> consultarUsarioPorId(@PathVariable Integer id) throws UsuarioException {
		Optional<Usuario> user = Optional.ofNullable(userService.getById(id));
		return user.map(ResponseEntity::ok)
				.orElseThrow(() -> new UsuarioException("User not found for ID " + id, null));
	}
	
	@RequestMapping(path=Constantes.Url.URL_USERS, method = RequestMethod.POST)
	public ResponseEntity<Usuario> salvarUsario(@RequestBody Usuario usuario) throws UsuarioException {
		userService.salvarUsuario(usuario);
		logger.info(String.format("Salvando nova usuario %s", usuario.getNome()));
		return ResponseEntity.ok(usuario);
	}
	
	@RequestMapping(path=Constantes.Url.URL_USERS + "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Usuario atualizarUsario(@RequestBody Usuario usuarioAtualizado, @PathVariable Integer id) throws UsuarioException {
		userService.updateUsuario(usuarioAtualizado, id);
		logger.info(String.format("Atualizando usuario %s", id));
		return usuarioAtualizado;
	}
	
	@RequestMapping(path=Constantes.Url.URL_USERS + "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void removerUsario(@PathVariable int id) throws NoSuchMessageException, UsuarioException{
		userService.removeUsuario(id);
		logger.info(String.format("Removendo usuario %s", id));
	}



}
