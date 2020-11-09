package com.tutorial.spring.state.machine.runner;

import com.tutorial.spring.state.machine.context.WorkingContext;
import com.tutorial.spring.state.machine.enums.OrderEventEnum;
import com.tutorial.spring.state.machine.enums.OrderStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppStartupRunner implements CommandLineRunner {

    //@Autowired
    //private StateMachine<OrderStateEnum, OrderEventEnum> stateMachine;

    @Autowired
    private StateMachineFactory<OrderStateEnum, OrderEventEnum> stateMachineFactory;

    @Override
    public void run(String... args) throws Exception {
        create();
        //stateMachine.sendEvent(OrderEventEnum.assemble);
    }

    //create sm
    private void create() {
        //generate id
        UUID id = UUID.randomUUID();
        StateMachine<OrderStateEnum, OrderEventEnum> stateMachine = stateMachineFactory.getStateMachine(id);
        stateMachine.start();

        stateMachine.sendEvent(OrderEventEnum.order);
        WorkingContext.getIdSmMap().put(id, stateMachine);
        stateMachine.sendEvent(OrderEventEnum.assemble);
    }
}
