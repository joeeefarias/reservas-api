package io.github.joefarias.apireservas.service;

import io.github.joefarias.apireservas.Exception.AnuncioJaCadastradoException;
import io.github.joefarias.apireservas.Exception.IdAnuncioNaoEncontradoException;
import io.github.joefarias.apireservas.domain.Anuncio;
import io.github.joefarias.apireservas.domain.Imovel;
import io.github.joefarias.apireservas.domain.Usuario;
import io.github.joefarias.apireservas.mapper.AnuncioMapper;
import io.github.joefarias.apireservas.repository.AnuncioRepository;
import io.github.joefarias.apireservas.service.request.CadastrarAnuncioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AnuncioService {

    private final AnuncioMapper anuncioMapper = AnuncioMapper.INSTANCE;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ImovelService imovelService;

    public Anuncio anunciarImovel(CadastrarAnuncioRequest anuncioRequest){
        if (anuncioRepository.existsByIdImovel(anuncioRequest.getIdImovel())){
            throw new AnuncioJaCadastradoException(anuncioRequest.getIdImovel());
        }
        Imovel imovel = imovelService.buscarImovelPorId(anuncioRequest.getIdImovel());
        Usuario usuario = usuarioService.buscarUsuarioPorId(anuncioRequest.getIdAnunciante());

        Anuncio anuncio = anuncioMapper.cadastrarAnuncioRequestToAnuncio(anuncioRequest);
        anuncio.setAnunciante(usuario);
        anuncio.setImovel(imovel);

        return anuncioRepository.save(anuncio);


    }

    public Boolean existeAnuncioPorIdImovel(Long id){
        return anuncioRepository.existsByIdImovel(id);
    }

    public Anuncio buscarAnuncioPorId(Long idAnuncio) {
        return anuncioRepository.findById(idAnuncio).orElseThrow(() ->
                new IdAnuncioNaoEncontradoException(idAnuncio));
    }

    public Page<Anuncio> listarAnuncios(Pageable pageable) {

        return anuncioRepository.findAll(pageable);
    }

    public Page<Anuncio> listarAnunciosPorAnunciante(Long idAnunciante, Pageable pageable) {

        return anuncioRepository.findImovelByIdAnunciante(idAnunciante, pageable);
    }

    public void excluirAnuncio(Long idAnuncio) {

        Anuncio anuncio = anuncioRepository.findById(idAnuncio).orElseThrow(() ->
                new IdAnuncioNaoEncontradoException(idAnuncio));
        anuncio.setDelflag('S');
        anuncioRepository.save(anuncio);
    }
}
