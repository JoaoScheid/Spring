package com.springRomario.aulaSpring1.controller;


import com.springRomario.aulaSpring1.model.dto.ContaPostRequestDTO;
import com.springRomario.aulaSpring1.model.entity.Conta;
import com.springRomario.aulaSpring1.service.ContaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/conta")
@AllArgsConstructor
public class ContaController {

    private ContaService service;
//    @RequestMapping(
//            method = RequestMethod.GET,
//            value = "/ola"
//    )
    @GetMapping
    public List<Conta> buscarTodasAsContas(){
        return service.buscarContas();
    }

    @GetMapping("/page")
    public Page<Conta> buscarTodasAsContasPaginado(@PageableDefault(size = 20,//Numero de elemento
            sort = "saldo", //Por ordem de saldo
            direction = Sort.Direction.DESC, //Do maior saldo pro menor
            page = 0 //Comecam na pagina zero
    )Pageable pageable){
        return service.buscarContas(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Conta cadastrarConta(@RequestBody @Valid ContaPostRequestDTO contaDTO){
        try {
            Conta conta = service.criarConta(contaDTO);
            return conta;
        }catch (SQLException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception internal){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public Conta buscarContaPorId(@PathVariable Integer id){
        return service.buscarConta(id);
    }

    @DeleteMapping("/{id}")
    public String excluirConta(@PathVariable Integer id){
        service.deletarConta(id);
        return "deletado com sucesso";
    }

    @PutMapping("/{id}")
    public Conta atualizarConta(@RequestBody Conta conta,@PathVariable Integer id){
        return service.atualizarConta(conta, id);
    }

    @PatchMapping("/{id}")
    public Conta alterarLimite(@RequestParam Double limite, @PathVariable Integer id){
        return service.alterarLimite(id, limite);
    }
}
