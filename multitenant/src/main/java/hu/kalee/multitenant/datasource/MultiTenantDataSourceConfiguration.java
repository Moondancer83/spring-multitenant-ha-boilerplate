package hu.kalee.multitenant.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import hu.kalee.multitenant.tenant.property.PropertyFileResolver;
import hu.kalee.multitenant.tenant.property.TenantInfo;

public class MultiTenantDataSourceConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(MultiTenantDataSourceConfiguration.class);

    @Value("${multitenant.tenant-id-source}")
    private String tenantIdSource;

    @Autowired
    private DataSourceProperties properties;

    @Autowired
    private PropertyFileResolver propertyFileResolver;

    /**
     * Defines the data source for the application
     *
     * @return datasource
     */
    @Bean
    public DataSource dataSource() {
        LOG.info("multitenant.tenant-id-source: {}", tenantIdSource);

        TenantInfo tenantInfo;

        try {
            tenantInfo = propertyFileResolver.getTenantInfo();
        } catch (RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }

        Map<Object, Object> resolvedDataSources = new HashMap<>();

        for (TenantInfo.Tenant tenant : tenantInfo.getTenants()) {
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(this.getClass().getClassLoader());

            String tenantId = tenant.getName();

            // Assumption: The tenant database uses the same driver class
            // as the default database that you configure.
            dataSourceBuilder.driverClassName(properties.getDriverClassName()).url(tenant.getDatasource().getUrl()).username(tenant.getDatasource().getUsername()).password(tenant.getDatasource().getPassword());

            if (properties.getType() != null) {
                dataSourceBuilder.type(properties.getType());
            }

            final DataSource dataSource = dataSourceBuilder.build();
            resolvedDataSources.put(tenantId, dataSource);
        }

        // Create the final multi-tenant source.
        // It needs a default database to connect to.
        // Make sure that the default database is actually an empty tenant database.
        // Don't use that for a regular tenant if you want things to be safe!
        MultitenantDataSource dataSource = new MultitenantDataSource();
        dataSource.setDefaultTargetDataSource(defaultDataSource());
        dataSource.setTargetDataSources(resolvedDataSources);

        LOG.info("------------ Tenant Overview ------------");
        resolvedDataSources.keySet().forEach(id -> LOG.info(" Tenant ID: {}", id));
        LOG.info("-----------------------------------------");

        // Call this to finalize the initialization of the data source.
        dataSource.afterPropertiesSet();

        return dataSource;
    }

    private DataSource defaultDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(this.getClass().getClassLoader()).driverClassName(properties.getDriverClassName()).url(properties.getUrl()).username(properties.getUsername()).password(properties.getPassword());

        if (properties.getType() != null) {
            dataSourceBuilder.type(properties.getType());
        }

        return dataSourceBuilder.build();
    }
}