package com.tutorial.spring.state.machine.action.impl;

import com.tutorial.spring.state.machine.action.BaseAction;
import com.tutorial.spring.state.machine.enums.OrderEventEnum;
import com.tutorial.spring.state.machine.enums.OrderStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Service;

@Service(value = "assembleAction")
@Slf4j
public class AssembleActionImpl implements BaseAction {

    @Override
    public void execute(StateContext<OrderStateEnum, OrderEventEnum> stateContext) {
        log.info("Starting to assemble for {}", stateContext.getStateMachine().getUuid());
    }
}
