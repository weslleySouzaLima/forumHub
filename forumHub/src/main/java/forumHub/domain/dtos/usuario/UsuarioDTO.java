package forumHub.domain.dtos.usuario;

import forumHub.domain.models.Usuario;

public record UsuarioDTO(
        Long id,
        String email
) {
    public UsuarioDTO(Usuario autor) {
        this(autor.getId(),autor.getEmail());
    }
}
