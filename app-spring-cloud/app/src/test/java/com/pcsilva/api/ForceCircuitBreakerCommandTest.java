package com.pcsilva.api;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.netflix.config.ConfigurationManager;


public class ForceCircuitBreakerCommandTest {

    @Test
    public void testForceOpen(){

        assertEquals(Boolean.TRUE, new FakeCommand().execute());

        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.FakeCommand.circuitBreaker.forceOpen", true);


        assertEquals(Boolean.FALSE, new FakeCommand().execute());

    }

    private class FakeCommand extends HystrixCommand<Boolean> {

        public FakeCommand(){
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TestGroup")));
        }

        @Override
        public Boolean run(){return Boolean.TRUE;}

        @Override
        public Boolean getFallback() {return Boolean.FALSE;}
    }
}