package com.kemisshop;

import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/*
    wontgn created on 12/11/20 inside the package - com.kemisshop
*/
/*@Profile("Config-Monitor")*/
@Configuration
@Import(RabbitAutoConfiguration.class)
public class ConfigMonitorConfiguration {
}
