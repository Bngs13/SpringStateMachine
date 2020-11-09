package com.tutorial.spring.state.machine.action;

import com.tutorial.spring.state.machine.enums.OrderEventEnum;
import com.tutorial.spring.state.machine.enums.OrderStateEnum;
import org.springframework.statemachine.action.Action;

public interface BaseAction extends Action<OrderStateEnum, OrderEventEnum> {

}
