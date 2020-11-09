package com.tutorial.spring.state.machine.enums;

public enum OrderEventEnum {
    order,
    assemble,
    deliver,
    releaseInvoice,
    paymentReceived,
    cancel,
    claim,
    reassemble
}
