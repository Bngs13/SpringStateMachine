package com.tutorial.spring.state.machine.context;

import com.tutorial.spring.state.machine.enums.OrderEventEnum;
import com.tutorial.spring.state.machine.enums.OrderStateEnum;
import org.springframework.statemachine.StateMachine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WorkingContext {
    private static Map<UUID, StateMachine<OrderStateEnum, OrderEventEnum>> idSmMap;

    public static Map<UUID, StateMachine<OrderStateEnum, OrderEventEnum>> getIdSmMap() {
        if (idSmMap == null)
            idSmMap = new HashMap<>();
        return idSmMap;
    }
}
