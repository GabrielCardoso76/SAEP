package com.example.SAEP.Controller;

import com.example.SAEP.Dto.AlunoDto;
import com.example.SAEP.Entity.Aluno;
import com.example.SAEP.Service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> getById(@PathVariable Long id){
        Optional<AlunoDto> alunoDto = alunoService.getById(id);
        if (alunoDto.isPresent()){
            return ResponseEntity.ok(alunoDto.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<AlunoDto> created(@RequestBody AlunoDto alunoDto){
        AlunoDto alunoDto1 = alunoService.save(alunoDto);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDto> update(@PathVariable Long id, @RequestBody AlunoDto alunoDto){
        Optional<AlunoDto> optionalAlunoDto = alunoService.updateAluno(id, alunoDto);
        if (optionalAlunoDto.isPresent()){
            return ResponseEntity.ok(optionalAlunoDto.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (alunoService.delete(id)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
