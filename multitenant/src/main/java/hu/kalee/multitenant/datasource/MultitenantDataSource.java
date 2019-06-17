package hu.kalee.multitenant.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import hu.kalee.multitenant.tenant.TenantContext;

public class MultitenantDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getCurrentTenant();
    }
}