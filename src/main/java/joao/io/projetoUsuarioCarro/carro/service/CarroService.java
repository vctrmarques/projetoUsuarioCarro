package joao.io.projetoUsuarioCarro.carro.service;

import java.util.List;

import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import joao.io.projetoUsuarioCarro.entity.Carro;
import joao.io.projetoUsuarioCarro.exception.UsuarioException;


@Service
public interface CarroService {
	
	public List<Carro> buscarCarro();
    public List<Carro> salvarCarro(List<Carro> carro) throws UsuarioException;
    public void removeCarro(int id) throws NoSuchMessageException, UsuarioException;
	public Carro getById(Integer id) throws UsuarioException;
	public Carro updateCarro(Carro carro, Integer id) throws NoSuchMessageException, UsuarioException;

}
