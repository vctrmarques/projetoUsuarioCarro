package joao.io.projetoUsuarioCarro.carro.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import joao.io.projetoUsuarioCarro.entity.Carro;
import joao.io.projetoUsuarioCarro.exception.UsuarioException;
import joao.io.projetoUsuarioCarro.repository.CarroRepository;

@Service("carroService")
public class CarroServiceImpl implements CarroService{

	@Autowired
	private CarroRepository carroRepository;
	
	@Autowired
    private MessageSource messageSource;
	
	@Override
	public List<Carro> buscarCarro() {
		return carroRepository.findAll();
	}

	@Override
	public List<Carro> salvarCarro(List<Carro> carros) throws UsuarioException {
		try {
			return carroRepository.saveAll(carros);
		}catch(Exception e) {
			e.printStackTrace();
			throw new UsuarioException(String.format(messageSource.getMessage("Usuario.erro.salvamento", null, Locale.getDefault()), ""),e);
		}
	}

	@Override
	public void removeCarro(int id) throws NoSuchMessageException, UsuarioException {
		try {
			Optional<Carro> carro = carroRepository.findById(id);
			if (carro.isPresent()) {
				carroRepository.delete(carro.get());
			}else {
				throw new UsuarioException(String.format(messageSource.getMessage("Carro.erro.Usuario.naoencontrado", null, Locale.getDefault()), id), null);
			}
		}catch(UsuarioException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new UsuarioException(String.format(messageSource.getMessage("Carro.erro.remocao", null, Locale.getDefault()), id),e);
		}
		
	}

	@Override
	public Carro getById(Integer id) throws UsuarioException {
		try {
			Optional<Carro> carro = carroRepository.findById(id);
			if (carro.isPresent()) {
				return carro.get();
			}else {
				throw new UsuarioException(String.format(messageSource.getMessage("Carro.erro.Usuario.naoencontrado", null, Locale.getDefault()), id), null);
			}
		}catch(UsuarioException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new UsuarioException(String.format(messageSource.getMessage("Carro.erro.consulta", null, Locale.getDefault()), id),e);
		}
	}

	@Override
	public Carro updateCarro(Carro carro, Integer id) throws NoSuchMessageException, UsuarioException {
		Optional<Carro> carroOld = carroRepository.findById(id);
		carroOld.get().setLicensePlate(carro.getLicensePlate());
		carroOld.get().setYear(carro.getYear());
		carroOld.get().setModel(carro.getModel());
		carroOld.get().setColor(carro.getColor());
		
		try {
			return carroRepository.save(carroOld.get());
		}catch(Exception e) {
			e.printStackTrace();
			throw new UsuarioException(String.format(messageSource.getMessage("Carro.erro.atualização", null, Locale.getDefault()), carro.getLicensePlate()),e);
		}
	}

	

}
