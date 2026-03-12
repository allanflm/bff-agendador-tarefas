package com.allan.bff_agendador.business;

import com.allan.bff_agendador.business.dto.in.LoginRequest;
import com.allan.bff_agendador.business.dto.out.TarefasDTOResponse;
import com.allan.bff_agendador.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;


    @Scheduled(cron = "${cron.horario}")
    public void BuscaTarefasProximaHora() {
        String token = login(converterParaRequestDto());
        log.info("Buscando tarefas agendadas para a próxima hora...");
        LocalDateTime horaInicio = LocalDateTime.now().minusMinutes(10);
        LocalDateTime horaFim = LocalDateTime.now().plusHours(1).plusMinutes(10);

        List<TarefasDTOResponse> listaDeTarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(
                horaInicio, horaFim, token);

        log.info("Encontradas {} tarefas.", listaDeTarefas.size());

        listaDeTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Enviando email para tarefa: {} com ID: {}", tarefa.getNomeDaTarefa(), tarefa.getId());
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
        });
        log.info("Processamento de tarefas agendadas para a próxima hora concluído.");
    }

    public String login(LoginRequest dto) {
        return usuarioService.loginUsuario(dto);
    }

    public LoginRequest converterParaRequestDto() {
        return LoginRequest.builder().email(email).senha(senha).build();
    }

}
