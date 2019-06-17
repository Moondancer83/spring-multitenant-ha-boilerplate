package hu.kalee.multitenant.tenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantContext {
    private static final Logger LOG = LoggerFactory.getLogger(TenantContext.class.getName());

    public static final String DEFAULT_TENANT = "default";

    private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

    static {
        setCurrentTenant(DEFAULT_TENANT);
    }

    private TenantContext() {
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(String tenant) {
        LOG.info("Setting tenant: {} ", tenant);
        currentTenant.set(tenant);
    }

    public static void clear() {
        LOG.info("Clearing tenant: {}", currentTenant);
        currentTenant.set(null);
    }
}