package com.learning.basics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SealedInterfaceSystemLoggerExampleTest {

    private final SealedInterfaceSystemLoggerExample service = new SealedInterfaceSystemLoggerExample();

    @Test
    void testAddEmployee () {
        SealedInterfaceSystemLoggerExample.Supervisor supervisor =
                new SealedInterfaceSystemLoggerExample.Supervisor(
                        123L, "supervisor1", 3);
        service.addEmployee(supervisor);
    }

}