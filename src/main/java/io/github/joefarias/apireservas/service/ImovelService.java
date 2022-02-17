package io.github.joefarias.apireservas.service;

import io.github.joefarias.apireservas.Exception.IdImovelNaoencontradoException;
import io.github.joefarias.apireservas.Exception.ImovelComAnuncioCadastradoException;
import io.github.joefarias.apireservas.domain.Endereco;
import io.github.joefarias.apireservas.domain.Imovel;
import io.github.joefarias.apireservas.domain.Usuario;
import io.github.joefarias.apireservas.mapper.ImovelMapper;
import io.github.joefarias.apireservas.repository.ImovelRepository;
import io.github.joefarias.apireservas.service.request.CadastrarImovelRequest;
import io.github.joefarias.apireservas.service.response.ImovelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImovelService {

    private final ImovelMapper imovelMapper = ImovelMapper.INSTANCE;

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AnuncioService anuncioService;


        public Imovel cadastrarImovel(CadastrarImovelRequest imovelRequest){

            Usuario usuario = usuarioService.buscarUsuarioPorId((imovelRequest.getIdProprietario()));

            Endereco endereco = criarEndereco(imovelRequest);

            Imovel imovel = criarImovel(imovelRequest, usuario, endereco);

            return imovelRepository.save(imovel);


        }

    private Imovel criarImovel(CadastrarImovelRequest imovelRequest, Usuario usuario, Endereco endereco) {
        Imovel imovel = new Imovel();
        imovel.setIdentificacao(imovelRequest.getIdentificacao());
        imovel.setTipoImovel(imovelRequest.getTipoImovel());
        imovel.setEndereco(endereco);
        imovel.setProprietario(usuario);
        imovel.setCaracteristicas(imovelRequest.getCaracteristicas());
        return imovel;
    }

    private Endereco criarEndereco(CadastrarImovelRequest imovelRequest) {
        return Endereco.builder().cep(imovelRequest.getEndereco().getCep()).
                logradouro(imovelRequest.getEndereco().getLogradouro()).
                numero(imovelRequest.getEndereco().getNumero()).
                complemento(imovelRequest.getEndereco().getComplemento()).
                bairro(imovelRequest.getEndereco().getBairro()).
                cidade(imovelRequest.getEndereco().getCidade()).
                estado(imovelRequest.getEndereco().getEstado()).build();
    }

    public Page<Imovel> listarImoveis(Pageable pageable){
        return imovelRepository.findAll(pageable);

        }


    public Page<Imovel> listarImoveisProprietario(Long idProprietario, Pageable pageable) {
        return imovelRepository.findByIdProprietarioIn(idProprietario, pageable);

    }

    public ImovelResponse buscarImovelResponsePorId(Long idImovel){

        return ImovelMapper.INSTANCE.imovelToImovelResponse(buscarImovelPorId(idImovel));
    }

    public Imovel buscarImovelPorId(Long idImovel){
           return imovelRepository.findById(idImovel).orElseThrow(() ->
                   new IdImovelNaoencontradoException(idImovel));
    }

    public void excluirImovel(Long id) {
        if (anuncioService.existeAnuncioPorIdImovel(id))
            throw new ImovelComAnuncioCadastradoException();

        Imovel imovel = buscarImovelPorId(id);
        imovel.setActive(false);
        imovelRepository.save(imovel);

    }
}


