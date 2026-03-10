package com.allan.bff_agendador.business;

import com.allan.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.allan.bff_agendador.business.dto.in.LoginRequest;
import com.allan.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.allan.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.allan.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.allan.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.allan.bff_agendador.business.dto.out.UsuarioDTOResponse;
import com.allan.bff_agendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {

        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginRequest usuarioDTO) {
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
      return usuarioClient.buscarUsuarioPorEmail(email,token);
    }

    public void deletarUsuarioPorEmail(String email, String token) {
         usuarioClient.deletarUsuario(email,token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto) {
       return usuarioClient.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
        return usuarioClient.atualizaEnderecoUsuario(enderecoDTO, token, idEndereco);

    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest dto, String token) {
        return usuarioClient.atualizaTelefone(dto, token, idTelefone);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {
        return usuarioClient.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto) {
        return usuarioClient.cadastraTelefone(dto, token);
    }


}