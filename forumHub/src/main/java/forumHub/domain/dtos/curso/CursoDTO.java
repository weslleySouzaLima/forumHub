package forumHub.domain.dtos.curso;


import forumHub.domain.models.Curso;

public record CursoDTO(
        String nome,
        Long id,
        String categoria
) {
    public CursoDTO(Curso curso) {
        this(curso.getNome(),curso.getId(), curso.getCategoria());
    }
}
