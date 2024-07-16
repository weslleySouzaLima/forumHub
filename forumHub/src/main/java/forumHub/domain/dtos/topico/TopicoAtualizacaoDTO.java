package forumHub.domain.dtos.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoAtualizacaoDTO(
        @NotNull
        Long id,

        @NotBlank
        String mensagem,

        @NotNull
        @Future  //tem q ser uma data no futuro
        LocalDateTime data,

        @NotBlank
        String status
) {

}
