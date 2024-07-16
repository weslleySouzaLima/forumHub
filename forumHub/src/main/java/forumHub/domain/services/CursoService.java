package forumHub.domain.services;

import forumHub.domain.dtos.curso.CursoAtualizacaoDTO;
import forumHub.domain.dtos.curso.CursoCadastroDTO;
import forumHub.domain.dtos.curso.CursoDTO;
import forumHub.domain.exceptions.CursoCantBeDelete;
import forumHub.domain.exceptions.CursoNotFoundException;
import forumHub.domain.models.Curso;
import forumHub.domain.repositorys.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private ICursoRepository cursoRepository;

    @Transactional
    public CursoDTO cadastrarCurso(CursoCadastroDTO dto) {
        Curso curso = new Curso(dto);
        this.cursoRepository.save(curso);
        var cursoDto = new CursoDTO(curso);
        return cursoDto;
    }


    public List<CursoDTO> listarCursos() {
        var lista = this.cursoRepository.findAll();
        var listagemCursos = lista.stream().map(CursoDTO::new).collect(Collectors.toList());
        return listagemCursos;
    }

    public CursoDTO buscarCurso(Long id) {
        var curso = this.cursoRepository.findById(id).orElseThrow(()-> new CursoNotFoundException("Curso não encontrado!"));
        var cusoDto = new CursoDTO(curso);
        return cusoDto;
    }

    @Transactional
    public CursoDTO atualizarCurso(CursoAtualizacaoDTO dto) {
        var curso = this.cursoRepository.findById(dto.id()).orElseThrow(()-> new CursoNotFoundException("Curso não encontrado!"));
        if (dto.nome() != null){
            curso.setNome(dto.nome());
        }
        if (dto.categoria() != null){
            curso.setCategoria(dto.categoria());
        }
        this.cursoRepository.save(curso);
        var cursoDto = new CursoDTO(curso);
        return cursoDto;
    }

    @Transactional
    public void deletarCursso(Long id) {
        var cusro = this.cursoRepository.findById(id).orElseThrow(()-> new CursoNotFoundException("Curso não encontrado!"));
        try {
            this.cursoRepository.delete(cusro);
        }catch (Exception e){
            throw new CursoCantBeDelete("O curso não pode ser deletado!");
        }
    }
}
