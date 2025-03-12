package com.springRomario.aulaSpring1.controller;

import com.springRomario.aulaSpring1.model.dto.ClientePostRequestDTO;
import com.springRomario.aulaSpring1.model.dto.ClientePostRequestDTO2;
import com.springRomario.aulaSpring1.model.dto.ClientePutRequestDTO;
import com.springRomario.aulaSpring1.model.dto.ClienteResponseDTO;
import com.springRomario.aulaSpring1.model.entity.Cliente;
import com.springRomario.aulaSpring1.service.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {


    private final ClienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO cadastrar(@Valid @RequestBody ClientePostRequestDTO2 clienteDTO){
         Cliente cliente = service.cadastrar(clienteDTO);
         return cliente.convertToClientResponseDTO();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO editar(@Valid @RequestBody ClientePutRequestDTO clienteDTO, @PathVariable Integer id){
        Cliente cliente = service.editar(id,clienteDTO);
        return cliente.convertToClientResponseDTO();
    }

   @PatchMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   public ClienteResponseDTO alterarContas(@Valid @PathVariable Integer id, @RequestParam Integer idConta){
        Cliente cliente = service.alterarConta(id,idConta);
        return cliente.convertToClientResponseDTO();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO buscarCliente(@PathVariable Integer id){
        Cliente cliente = service.buscar(id);
        return cliente.convertToClientResponseDTO();
    }

    @GetMapping("/nome")
    public ClienteResponseDTO buscarPorNome(@RequestParam String nome){
       return service.buscarPorNome(nome).convertToClientResponseDTO();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClienteResponseDTO> buscarClientes(@PageableDefault(size = 20, sort = "nome", direction = Sort.Direction.ASC, page = 0) Pageable pageable){
        Page<Cliente> clientePage = service.buscarConta(pageable);
        List<ClienteResponseDTO> contentList =
                clientePage.getContent().stream().map(
                Cliente::convertToClientResponseDTO).toList();
                return new PageImpl<>(contentList, clientePage.getPageable(), clientePage.getTotalElements());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String removerCliente(@PathVariable Integer id){
        service.remover(id);
        return "deletado com sucesso";
    }



}
