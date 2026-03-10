package com.allan.bff_agendador.controller;


import com.allan.bff_agendador.business.UsuarioService;
import com.allan.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.allan.bff_agendador.business.dto.in.LoginRequest;
import com.allan.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.allan.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.allan.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.allan.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.allan.bff_agendador.business.dto.out.UsuarioDTOResponse;
import com.allan.bff_agendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login de usuarios")
@SecurityRequirement(name = SecurityConfig.SECURTY_SCHEME)
public class UsuarioControlller {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salva Usuario", description = "Salva um novo usuario no sistema")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario ja cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {

        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuario", description = "Login usuario no sistema")
    @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public String login(@RequestBody LoginRequest usuarioDTO) {
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de Usuarios por email", description = "Busca os dados do usuario logado no sistema atraves do email")
    @ApiResponse(responseCode = "200", description = "Dados do usuario encontrados com sucesso")
    @ApiResponse(responseCode = "404", description = "Email do usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuarios por id", description = "Deleta o usuario logado no sistema atraves do email")
    @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Email do usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String email,
                                               @RequestHeader(name = "Authorization", required = false) String token) {
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza dados de usuario",
            description = "Atualiza os dados do usuario logado no sistema, como nome e senha, atraves do email")
    @ApiResponse(responseCode = "200", description = "Dados do usuario atualizados com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza enderecos de usuario", description = "Atualiza os enderecos do usuario logado no sistema, atraves do id do endereco")
    @ApiResponse(responseCode = "200", description = "Endereco do usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereco do usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEnderecoUsuario(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                       @RequestHeader(name = "Authorization", required = false) String token,
                                                                       @RequestParam("id") Long id) {

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone de usuario", description = "Atualiza os telefone do usuario logado no sistema, atraves do id do endereco")
    @ApiResponse(responseCode = "200", description = "Telefone do usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Telefone do usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token,
                                                                @RequestParam("id") Long id) {

        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva endereco de usuario",
            description = "Salva um novo endereco para o usuario logado no sistema, atraves do email")
    @ApiResponse(responseCode = "200", description = "Endereco do usuario cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastra telefone de usuario", description = "Cadastra os telefone do usuario logado no sistema, atraves do id do endereco")
    @ApiResponse(responseCode = "200", description = "Telefone do usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

}
