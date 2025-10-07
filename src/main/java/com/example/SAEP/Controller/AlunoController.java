package com.example.SAEP.Controller;

import com.example.SAEP.Dto.AlunoDto;
import com.example.SAEP.Entity.Aluno;
import com.example.SAEP.Service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/alunos") // Usamos "/alunos" como URL base para as operações
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    /**
     * Exibe a lista de todos os alunos.
     */
    @GetMapping
    public String listarAlunos(Model model) {
        model.addAttribute("alunos", alunoService.getAll());
        return "alunos"; // Retorna o nome do arquivo: /resources/templates/alunos.html
    }

    /**
     * Exibe o formulário para adicionar um novo aluno.
     */
    @GetMapping("/novo")
    public String mostrarFormularioDeAdicao(Model model) {
        // Cria um objeto aluno para preencher o formulário
        Aluno aluno = new Aluno();
        model.addAttribute("aluno", aluno);
        return "form-aluno"; // Retorna o arquivo: /resources/templates/form-aluno.html
    }

    /**
     * Salva um aluno novo ou atualiza um existente.
     */
    @PostMapping("/salvar")
    public String salvarAluno(@ModelAttribute("aluno") Aluno aluno) {
        // Converte o Aluno (da view) para AlunoDto (para o serviço)
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(aluno.getId());
        alunoDto.setNome(aluno.getNome());
        alunoDto.setSobrenome(aluno.getSobrenome());
        alunoDto.setCpf(aluno.getCpf());
        alunoDto.setCurso(aluno.getCurso());

        // Se o ID não for nulo, é uma atualização; senão, é uma inserção.
        if (aluno.getId() != null) {
            alunoService.updateAluno(aluno.getId(), alunoDto);
        } else {
            alunoService.save(alunoDto);
        }
        return "redirect:/alunos"; // Redireciona para a lista de alunos
    }

    /**
     * Exibe o formulário para editar um aluno existente.
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicao(@PathVariable Long id, Model model) {
        Optional<AlunoDto> alunoDtoOpt = alunoService.getById(id);
        if (alunoDtoOpt.isPresent()) {
            // Converte DTO para Aluno para popular o formulário
            AlunoDto alunoDto = alunoDtoOpt.get();
            Aluno aluno = new Aluno(alunoDto.getId(), alunoDto.getNome(), alunoDto.getCpf(), alunoDto.getSobrenome(), alunoDto.getCurso());
            model.addAttribute("aluno", aluno);
            return "form-aluno";
        } else {
            return "redirect:/alunos"; // Se não encontrar, volta para a lista
        }
    }

    /**
     * Deleta um aluno pelo ID.
     */
    @GetMapping("/deletar/{id}")
    public String deletarAluno(@PathVariable Long id) {
        alunoService.delete(id);
        return "redirect:/alunos";
    }
}
