package hu.kalee.multitenant;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import hu.kalee.multitenant.datasource.MultiTenantDataSourceConfiguration;

@Configuration
@ComponentScan("hu.kalee.multitenant.tenant")
@Import(MultiTenantDataSourceConfiguration.class)
public class MultiTenancyConfiguration {
}
