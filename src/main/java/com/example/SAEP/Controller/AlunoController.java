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
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;


    @GetMapping
    public String listarAlunos(Model model) {
        model.addAttribute("alunos", alunoService.getAll());
        return "alunos";
    }

    @GetMapping("/novo")
    public String mostrarFormularioDeAdicao(Model model) {
        Aluno aluno = new Aluno();
        model.addAttribute("aluno", aluno);
        return "form-aluno";
    }

    @PostMapping("/salvar")
    public String salvarAluno(@ModelAttribute("aluno") Aluno aluno) {

        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(aluno.getId());
        alunoDto.setNome(aluno.getNome());
        alunoDto.setSobrenome(aluno.getSobrenome());
        alunoDto.setCpf(aluno.getCpf());
        alunoDto.setCurso(aluno.getCurso());


        if (aluno.getId() != null) {
            alunoService.updateAluno(aluno.getId(), alunoDto);
        } else {
            alunoService.save(alunoDto);
        }
        return "redirect:/alunos";
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicao(@PathVariable Long id, Model model) {
        Optional<AlunoDto> alunoDtoOpt = alunoService.getById(id);
        if (alunoDtoOpt.isPresent()) {

            AlunoDto alunoDto = alunoDtoOpt.get();
            Aluno aluno = new Aluno(alunoDto.getId(), alunoDto.getNome(), alunoDto.getCpf(), alunoDto.getSobrenome(), alunoDto.getCurso());
            model.addAttribute("aluno", aluno);
            return "form-aluno";
        } else {
            return "redirect:/alunos";
        }
    }


    @GetMapping("/deletar/{id}")
    public String deletarAluno(@PathVariable Long id) {
        alunoService.delete(id);
        return "redirect:/alunos";
    }
}
