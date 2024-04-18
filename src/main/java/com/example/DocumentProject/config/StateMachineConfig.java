package com.example.DocumentProject.config;

import com.example.DocumentProject.stateMashine.action.DocumentState;
import com.example.DocumentProject.stateMashine.event.DocumentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.Optional;


@Configuration
@EnableStateMachineFactory //для того что бы каждый раз создавалась новая StateMashine
@Slf4j
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<DocumentState, DocumentEvent> {
    @Override
    public void configure(StateMachineConfigurationConfigurer<DocumentState, DocumentEvent> config) throws Exception {

        config.withConfiguration()
                .listener(listener())
                .autoStartup(true);
    }

    //логи перемещения между состояниями  настройки методов LISTENER
    private StateMachineListener<DocumentState, DocumentEvent> listener() {
        return new StateMachineListenerAdapter<DocumentState, DocumentEvent>(){
            @Override
            public void transition(Transition<DocumentState, DocumentEvent> transition) {
                log.info("MOVE from {} to {}",
                        ofNullableState(transition.getSource()),
                        ofNullableState(transition.getTarget()));
            }
            private Object ofNullableState(State s){
                return Optional.ofNullable(s)
                        .map(State::getId)
                        .orElse(null);
            }

            @Override
            public void eventNotAccepted(Message<DocumentEvent> event) {
                log.error("not accepted: {}", event);
            }
        };
    }

    // начальная конфигурация, показываем какие состояния имеются
    @Override
    public void configure(StateMachineStateConfigurer<DocumentState, DocumentEvent> states) throws Exception {
        states.withStates()
                .initial(DocumentState.NEW)
                .state(DocumentState.EXECUTION, executionAction())
                .state(DocumentState.CONTROL, controlAction())
                .state(DocumentState.REVISION, revisionAction())
                .end(DocumentState.ACCEPTANCE);
    }

    //логи действий выводятся в консоль
    private Action<DocumentState, DocumentEvent> executionAction() {
        return context -> log.warn("Файл отправлен на исполнение");
    }
    private Action<DocumentState, DocumentEvent> controlAction() {
        return context -> log.warn("Файл отправлен на проверку");
    }

    private Action<DocumentState, DocumentEvent> revisionAction() {
        return context -> log.warn("Файл отправлен на доработку");
    }
    private Action<DocumentState, DocumentEvent> acceptanceAction() {
        return context -> log.warn("Файл отправлен на прием");
    }


    // переходы между состояниями и события которе должны произойти для изменения состояния
    @Override
    public void configure(StateMachineTransitionConfigurer<DocumentState, DocumentEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(DocumentState.NEW)
                .target(DocumentState.EXECUTION)
                .event(DocumentEvent.START_EXECUTION)
                .and()

                .withExternal()
                .source(DocumentState.EXECUTION)
                .target(DocumentState.CONTROL)
                .event(DocumentEvent.START_CONTROL)
                .and()

                .withExternal()
                .source(DocumentState.CONTROL)
                .target(DocumentState.REVISION)
                .event(DocumentEvent.START_REVISION)
                .and()

                .withExternal()
                .source(DocumentState.REVISION)
                .target(DocumentState.EXECUTION)
                .event(DocumentEvent.START_EXECUTION_AFTER_REVISION)
                .and()

                .withExternal()
                .source(DocumentState.CONTROL)
                .target(DocumentState.ACCEPTANCE)
                .event(DocumentEvent.START_ACCEPTANCE)
                .action(acceptanceAction());

    }
}
