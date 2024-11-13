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

import joao.io.projetoUsuarioCarro.carro.service.CarroService;
import joao.io.projetoUsuarioCarro.entity.Carro;
import joao.io.projetoUsuarioCarro.entity.Usuario;
import joao.io.projetoUsuarioCarro.exception.UsuarioException;
import joao.io.projetoUsuarioCarro.service.UsuarioService;
import joao.io.projetoUsuarioCarro.util.Constantes;

@RestController
public class RestCarroController {

	@Autowired
	private CarroService carroService;

	Logger logger = LoggerFactory.getLogger(RestCarroController.class);

	@RequestMapping(path = Constantes.Url.URL_USERS, method = RequestMethod.GET)
	public ResponseEntity<List<Carro>> getAllUser() {
		return ResponseEntity.ok(carroService.buscarCarro());
	}
	
	@RequestMapping(path = Constantes.Url.URL_USERS + "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Carro> consultarUsarioPorId(@PathVariable Integer id) throws UsuarioException {
		Optional<Carro> user = Optional.ofNullable(carroService.getById(id));
		return user.map(ResponseEntity::ok)
				.orElseThrow(() -> new UsuarioException("User not found for ID " + id, null));
	}
	
	@RequestMapping(path=Constantes.Url.URL_USERS, method = RequestMethod.POST)
	public ResponseEntity< List<Carro>> salvarCarro(@RequestBody List<Carro> carro) throws UsuarioException {
		carroService.salvarCarro(carro);
		logger.info(String.format("Salvando novos carros"));
		return ResponseEntity.ok(carro);
	}
	
	@RequestMapping(path=Constantes.Url.URL_USERS + "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Carro atualizarCarro(@RequestBody Carro carroAtualizado, @PathVariable Integer id) throws UsuarioException {
		carroService.updateCarro(carroAtualizado, id);
		logger.info(String.format("Atualizando carro %s", id));
		return carroAtualizado;
	}
	
	@RequestMapping(path=Constantes.Url.URL_USERS + "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void removerCarro(@PathVariable int id) throws NoSuchMessageException, UsuarioException{
		carroService.removeCarro(id);
		logger.info(String.format("Removendo carro %s", id));
	}



}
