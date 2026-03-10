package com.allan.bff_agendador.infrastructure.client;


import com.allan.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.allan.bff_agendador.business.dto.in.LoginRequest;
import com.allan.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.allan.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.allan.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.allan.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.allan.bff_agendador.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequest usuarioDTO);

    @GetMapping("/usuario")
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader(name = "Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader(name = "Authorization") String token);

    @DeleteMapping("/{email}")
    Void deletarUsuario(@PathVariable("email") String email,
                        @RequestHeader(name = "Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEnderecoUsuario(@RequestBody EnderecoDTORequest enderecoDTO,
                                                @RequestHeader(name = "Authorization") String token,
                                                @RequestParam("id") Long id);

    // Corrigido aqui: Adicionado @RequestBody e @RequestParam
    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader(name = "Authorization") String token,
                                         @RequestParam("id") Long idTelefone);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader(name = "Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader(name = "Authorization") String token);
}