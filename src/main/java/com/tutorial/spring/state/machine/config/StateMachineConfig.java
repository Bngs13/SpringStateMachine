package com.tutorial.spring.state.machine.config;

import com.tutorial.spring.state.machine.action.BaseAction;
import com.tutorial.spring.state.machine.action.impl.OrderActionImpl;
import com.tutorial.spring.state.machine.enums.OrderEventEnum;
import com.tutorial.spring.state.machine.enums.OrderStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
//@EnableStateMachine //the context that an instance of a SM can be built and started immediately on application startup
@EnableStateMachineFactory //-> Instance of SM is not created immediately on startup but started through the factory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStateEnum, OrderEventEnum> {


    //actions are components that you can use to interact and collaborate with a state machine
    @Autowired
    @Qualifier("assembleAction")
    private BaseAction assembleAction;

    @Autowired
    @Qualifier("orderedAction")
    private BaseAction orderAction;

    //for introducing states to sm
    @Override
    public void configure(StateMachineStateConfigurer<OrderStateEnum, OrderEventEnum> states) throws Exception {
        states.withStates()
                .initial(OrderStateEnum.ORDERED)
                .state(OrderStateEnum.ORDERED).stateEntry(OrderStateEnum.ORDERED, orderAction).stateExit(OrderStateEnum.ORDERED, assembleAction)
                .end(OrderStateEnum.PAYED)
                .states(EnumSet.allOf(OrderStateEnum.class));
    }

    //for transaction
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStateEnum, OrderEventEnum> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderStateEnum.ORDERED)
                .target(OrderStateEnum.ASSEMBLED)
                .event(OrderEventEnum.assemble)
                .and()
                .withExternal()
                .source(OrderStateEnum.ASSEMBLED)
                .target(OrderStateEnum.DELIVERED)
                .event(OrderEventEnum.deliver)
                .and()
                .withExternal()
                .source(OrderStateEnum.DELIVERED)
                .target(OrderStateEnum.INVOICED)
                .event(OrderEventEnum.releaseInvoice)
                .and()
                .withExternal()
                .source(OrderStateEnum.INVOICED)
                .target(OrderStateEnum.PAYED)
                .event(OrderEventEnum.paymentReceived)
                .and()
                .withExternal()
                .source(OrderStateEnum.ORDERED)
                .target(OrderStateEnum.CANCELLED)
                .event(OrderEventEnum.cancel)
                .and()
                .withExternal()
                .source(OrderStateEnum.ASSEMBLED)
                .target(OrderStateEnum.CANCELLED)
                .event(OrderEventEnum.cancel)
                .and()
                .withExternal()
                .source(OrderStateEnum.DELIVERED)
                .target(OrderStateEnum.RETURNED)
                .event(OrderEventEnum.claim)
                .and()
                .withExternal()
                .source(OrderStateEnum.INVOICED)
                .target(OrderStateEnum.RETURNED)
                .event(OrderEventEnum.claim)
                .and()
                .withExternal()
                .source(OrderStateEnum.RETURNED)
                .target(OrderStateEnum.CANCELLED)
                .event(OrderEventEnum.cancel)
                .and()
                .withExternal()
                .source(OrderStateEnum.RETURNED)
                .target(OrderStateEnum.ASSEMBLED)
                .event(OrderEventEnum.reassemble);
    }

    //for adding listener
    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStateEnum, OrderEventEnum> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true) //TODO: search autostartup
                .listener(new CustomStateMachineListenerAdapter());
    }
}
