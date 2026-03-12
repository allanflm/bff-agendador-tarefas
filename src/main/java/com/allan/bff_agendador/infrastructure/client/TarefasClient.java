package com.allan.bff_agendador.infrastructure.client;

import com.allan.bff_agendador.business.dto.in.TarefasDTORequest;
import com.allan.bff_agendador.business.dto.out.TarefasDTOResponse;
import com.allan.bff_agendador.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse gravarTarefa(@RequestBody TarefasDTORequest dto,
                                    @RequestHeader(name = "Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> buscaTarefasPorEmail(@RequestHeader(name = "Authorization") String token);

    @DeleteMapping
    Void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader(name = "Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alteraStatusNotificacaoTarefa(@RequestParam("status") StatusNotificacaoEnum status,
                                                     @RequestParam("id") String id,
                                                     @RequestHeader(name = "Authorization") String token);

    @PutMapping
    TarefasDTOResponse updateTarefa(@RequestBody TarefasDTORequest dto, @RequestParam("id") String id, @RequestHeader(name = "Authorization") String token);
}