package forumHub.domain.dtos.curso;

import jakarta.validation.constraints.NotBlank;

public record CursoCadastroDTO(
        @NotBlank
        String nome,
        String categoria
) {
}
