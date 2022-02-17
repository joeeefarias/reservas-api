package io.github.joefarias.apireservas.service;


import io.github.joefarias.apireservas.Exception.CpfJacadastradoException;
import io.github.joefarias.apireservas.Exception.CpfNaoEncontradoException;
import io.github.joefarias.apireservas.Exception.EmailJacadastradoException;
import io.github.joefarias.apireservas.Exception.IdUsuarioNaoEncontradoException;
import io.github.joefarias.apireservas.domain.Usuario;
import io.github.joefarias.apireservas.integration.AvatarService;
import io.github.joefarias.apireservas.mapper.UsuarioMapper;
import io.github.joefarias.apireservas.repository.UsuarioRepository;
import io.github.joefarias.apireservas.service.request.AtualizarUsuarioRequest;
import io.github.joefarias.apireservas.service.request.UsuarioRequest;
import io.github.joefarias.apireservas.service.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvatarService avatarService;

    public UsuarioResponse cadastraUsuario(UsuarioRequest usuarioRequest) {

        if (usuarioRepository.existsByEmail(usuarioRequest.getEmail()))
            throw new EmailJacadastradoException(usuarioRequest.getEmail());

        if (usuarioRepository.existsByCpf(usuarioRequest.getCpf()))
            throw new CpfJacadastradoException(usuarioRequest.getCpf());

        Usuario usuario = UsuarioMapper.INSTANCE.usuarioRequestToUsuario(usuarioRequest);
        usuario.setAvatarUrl(avatarService.buscarAvatar().getLink());
        usuario = usuarioRepository.save(usuario);
        return UsuarioMapper.INSTANCE.usuarioToUsuarioResponse(usuario);

    }

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }


    public UsuarioResponse buscarUsuarioPorCpf(String cpf) {

        Usuario usuario = usuarioRepository.findByCpf(cpf);
        if(usuario == null){
            throw new CpfNaoEncontradoException(cpf);
        }

        return UsuarioMapper.INSTANCE.usuarioToUsuarioResponse(usuario);
    }

    public UsuarioResponse buscarUsuarioResponsePorId(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() ->
                new IdUsuarioNaoEncontradoException(idUsuario));

        return UsuarioMapper.INSTANCE.usuarioToUsuarioResponse(usuario);

    }

    public Usuario buscarUsuarioPorId(Long idUsuario){
        return usuarioRepository.findById((idUsuario)).orElseThrow(() ->
                new IdUsuarioNaoEncontradoException((idUsuario)));
    }

    public UsuarioResponse alteraUsuarioPorId(Long id, AtualizarUsuarioRequest usuarioRequest) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() ->
                new IdUsuarioNaoEncontradoException(id));

        if (!usuario.getEmail().equals(usuarioRequest.getEmail())){
            if(usuarioRepository.existsByEmail(usuarioRequest.getEmail()))
                throw new EmailJacadastradoException(usuarioRequest.getEmail());
        }

        usuario.setNome(usuarioRequest.getNome());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(usuarioRequest.getSenha());
        usuario.setDataNascimento(usuarioRequest.getDataNascimento());
        usuario.getEndereco().setCep(usuarioRequest.getEndereco().getCep());
        usuario.getEndereco().setLogradouro(usuarioRequest.getEndereco().getLogradouro());
        usuario.getEndereco().setNumero(usuarioRequest.getEndereco().getNumero());
        usuario.getEndereco().setComplemento(usuarioRequest.getEndereco().getComplemento());
        usuario.getEndereco().setBairro(usuarioRequest.getEndereco().getBairro());
        usuario.getEndereco().setCidade(usuarioRequest.getEndereco().getCidade());
        usuario.getEndereco().setEstado(usuarioRequest.getEndereco().getEstado());

        usuarioRepository.save(usuario);

        return UsuarioMapper.INSTANCE.usuarioToUsuarioResponse(usuario);
    }
}
