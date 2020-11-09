package com.tutorial.spring.state.machine.action.impl;

import com.tutorial.spring.state.machine.action.BaseAction;
import com.tutorial.spring.state.machine.enums.OrderEventEnum;
import com.tutorial.spring.state.machine.enums.OrderStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Service;

@Service(value = "orderedAction")
@Slf4j
public class OrderActionImpl implements BaseAction {

    @Override
    public void execute(StateContext<OrderStateEnum, OrderEventEnum> stateContext) {
        log.info("Order Action triggered");
    }
}
