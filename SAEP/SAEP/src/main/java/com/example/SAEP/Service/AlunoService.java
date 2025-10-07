package com.example.SAEP.Service;

import com.example.SAEP.Dto.AlunoDto;
import com.example.SAEP.Entity.Aluno;
import com.example.SAEP.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno fromDTO(AlunoDto alunoDTO){
        Aluno aluno = new Aluno();

        aluno.setId(alunoDTO.getId());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setNome(alunoDTO.getNome());
        aluno.setSobrenome(alunoDTO.getSobrenome());
        aluno.setCurso(alunoDTO.getCurso());


        return aluno;
    }


    public AlunoDto toDTO(Aluno aluno){
        AlunoDto alunoDto = new AlunoDto();

        alunoDto.setId(aluno.getId());
        alunoDto.setCpf(aluno.getCpf());
        aluno.setNome(aluno.getNome());
        aluno.setSobrenome(aluno.getSobrenome());
        aluno.setCurso(aluno.getCurso());

        return alunoDto;
    }

    public List<Aluno> getAll(){
        return alunoRepository.findAll();
    }

    public Optional<AlunoDto> getById(Long id){
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);

        if (optionalAluno.isPresent()){
            return Optional.of(this.toDTO(optionalAluno.get()));
        }else {
            return Optional.empty();
        }
    };

    public AlunoDto save(AlunoDto alunoDto){
        Aluno aluno = this.fromDTO(alunoDto);

        Aluno alunobd = alunoRepository.save(aluno);

        return this.toDTO(alunobd);
    }

    public Optional<AlunoDto> updateAluno(Long id, AlunoDto alunoDto){

        Optional<Aluno> optionalAluno = alunoRepository.findById(id);

        if (optionalAluno.isPresent()){
            Aluno aluno = optionalAluno.get();

            aluno.setCpf(alunoDto.getCpf());
            aluno.setNome(alunoDto.getNome());
            aluno.setSobrenome(alunoDto.getSobrenome());
            aluno.setCurso(alunoDto.getCurso());

            Aluno alunoupdate = alunoRepository.save(aluno);

            return Optional.of(this.toDTO(alunoupdate));
        }else {
            return Optional.empty();
        }
    }
    public boolean   delete(Long id){
        if (alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
