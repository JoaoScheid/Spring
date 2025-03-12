package com.springRomario.aulaSpring1.controller;


import com.springRomario.aulaSpring1.model.dto.ContaPostRequestDTO;
import com.springRomario.aulaSpring1.model.dto.ContaPutRequestDTO;
import com.springRomario.aulaSpring1.model.dto.ContaResponseDTO;
import com.springRomario.aulaSpring1.model.entity.Conta;
import com.springRomario.aulaSpring1.service.ContaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public List<ContaResponseDTO> buscarTodasAsContas(){
        List<Conta> contaList = service.buscarContas();
        return contaList.stream().map(Conta::convertToContaResponseDTO).toList();
    }

    @GetMapping("/filtro")
    public List<ContaResponseDTO> buscarPorFiltro(@RequestParam String titular, @RequestParam Integer numero) {
        List<Conta> contasList =service.buscarContasFiltro(titular, numero);
        return contasList.stream().map(Conta::convertToContaResponseDTO).toList();
    }

    @GetMapping("/saldo")
    public List<ContaResponseDTO> buscarPorSaldo(@RequestParam Double saldo) {
        return service.buscarContasPorSaldo(saldo).stream().map(Conta::convertToContaResponseDTO).toList();
    }

    @GetMapping("/page")
    public Page<ContaResponseDTO> buscarTodasAsContasPaginado(@PageableDefault(size = 20,//Numero de elemento
            sort = "saldo", //Por ordem de saldo
            direction = Sort.Direction.DESC, //Do maior saldo pro menor
            page = 0 //Comecam na pagina zero
    )Pageable pageable){
        Page<Conta> contasPage = service.buscarContas(pageable);
        List<ContaResponseDTO> contasResponseList = contasPage.stream().map(Conta::convertToContaResponseDTO).toList();
        return new PageImpl<>(contasResponseList,contasPage.getPageable(), contasPage.getTotalElements());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponseDTO cadastrarConta(@RequestBody @Valid ContaPostRequestDTO contaDTO){
            Conta conta = service.criarConta(contaDTO);
            return conta.convertToContaResponseDTO();
    }

    @GetMapping("/{id}")
    public ContaResponseDTO buscarContaPorId(@PathVariable Integer id){
        Conta conta = service.buscarConta(id);
        return conta.convertToContaResponseDTO();
    }

    @DeleteMapping("/{id}")
    public String excluirConta(@PathVariable Integer id){
        service.deletarConta(id);
        return "deletado com sucesso";
    }

    @PutMapping("/{id}")
    public ContaResponseDTO atualizarConta(@RequestBody ContaPutRequestDTO contaDTO, @PathVariable Integer id){
        Conta conta = service.atualizarConta(contaDTO, id);
        return conta.convertToContaResponseDTO();
    }

    @PatchMapping("/{id}")
    public ContaResponseDTO alterarLimite(@RequestParam Double limite, @PathVariable Integer id){
       Conta conta = service.alterarLimite(id, limite);
       return conta.convertToContaResponseDTO();
    }
}
