package com.tutorial.spring.state.machine.config;

import com.tutorial.spring.state.machine.enums.OrderEventEnum;
import com.tutorial.spring.state.machine.enums.OrderStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.Objects;

@Slf4j
public class CustomStateMachineListenerAdapter extends StateMachineListenerAdapter<OrderStateEnum, OrderEventEnum> {

    @Override
    public void stateChanged(State<OrderStateEnum, OrderEventEnum> from, State<OrderStateEnum, OrderEventEnum> to) {
        if (Objects.isNull(from)) {
            log.info("Starting to state machine");
            return;
        }
        log.info("State updated from {} to {}", from.getId().toString(), to.getId().toString());
    }
}
