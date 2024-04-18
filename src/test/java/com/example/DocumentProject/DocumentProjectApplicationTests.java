package com.example.DocumentProject;

import com.example.DocumentProject.config.StateMachineConfig;
import com.example.DocumentProject.stateMashine.action.DocumentState;
import com.example.DocumentProject.stateMashine.event.DocumentEvent;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

@SpringBootTest
class DocumentProjectApplicationTests {

	@Autowired
	private StateMachineFactory<DocumentState, DocumentEvent> stateMachineFactory;
	private StateMachine<DocumentState, DocumentEvent> stateMachine;
	
	@BeforeEach
	public void setUp() throws Exception{
		stateMachine = stateMachineFactory.getStateMachine();
	}

	@Test
	public void testInit(){
		Assertions.assertNotNull(stateMachine);

	}
	@Test
	public void testInitState(){
		Assertions.assertEquals(stateMachine.getState().getId(), DocumentState.NEW); // может быть ошибка так как перед ним метод который перещелкивает конфигурацию stateMashine
	}
	@Test
	public void testGreenWay(){
		stateMachine.sendEvent(DocumentEvent.START_EXECUTION);
		stateMachine.sendEvent(DocumentEvent.START_CONTROL);
		stateMachine.sendEvent(DocumentEvent.START_ACCEPTANCE);

		Assertions.assertEquals(stateMachine.getState().getId(), DocumentState.ACCEPTANCE);
	}
	@Test
	public void wrongWay(){
		stateMachine.sendEvent(DocumentEvent.START_EXECUTION);
		stateMachine.sendEvent(DocumentEvent.START_ACCEPTANCE);

		Assertions.assertEquals(stateMachine.getState().getId(), DocumentState.EXECUTION);
	}


}
