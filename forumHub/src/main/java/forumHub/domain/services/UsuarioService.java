package forumHub.domain.services;

import forumHub.domain.dtos.usuario.UsuarioAtualizacaoDTO;
import forumHub.domain.dtos.usuario.UsuarioCadastroDTO;
import forumHub.domain.dtos.usuario.UsuarioDTO;
import forumHub.domain.exceptions.UsuarioCantBeDelete;
import forumHub.domain.exceptions.UsuarioNotFoundException;
import forumHub.domain.models.Usuario;
import forumHub.domain.repositorys.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.usuarioRepository.findByEmail(email);
    }

    @Transactional
    public UsuarioDTO cadastrarUsuario(UsuarioCadastroDTO dto) {
        Usuario usuario = new Usuario(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        this.usuarioRepository.save(usuario);
        var usuarioDto = new UsuarioDTO(usuario);
        return usuarioDto;
    }

    public List<UsuarioDTO> listarUsuarios() {
        var lista = this.usuarioRepository.findAll();
        var listagemUsuarios = lista.stream().map(UsuarioDTO::new).collect(Collectors.toList());
        return listagemUsuarios;
    }

    public UsuarioDTO buscarUsuario(Long id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(()-> new  UsuarioNotFoundException("O usúario buscado não foi encontrado!"));
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return  usuarioDTO;
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(UsuarioAtualizacaoDTO dto) {
        Usuario usuario = this.usuarioRepository.findById(dto.id()).orElseThrow(()-> new  UsuarioNotFoundException("O usúario não foi encontrado!"));
        if (dto.senha() != null){
            usuario.setSenha(passwordEncoder.encode(dto.senha()));
        }
        if (dto.nome() != null){
            usuario.setNome(dto.nome());
        }
        this.usuarioRepository.save(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return usuarioDTO;
    }


    @Transactional
    public void deletarUsuario(Long id) {
        buscarUsuario(id);
        try {
            this.usuarioRepository.deleteById(id);
        }catch (Exception e){
            throw new UsuarioCantBeDelete("O usuário não pode ser deletado!");
        }
    }
}

