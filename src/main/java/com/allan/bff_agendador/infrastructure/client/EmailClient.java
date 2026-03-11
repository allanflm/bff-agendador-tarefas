package com.allan.bff_agendador.infrastructure.client;


import com.allan.bff_agendador.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    Void envialEmail(@RequestBody TarefasDTOResponse dto);
}