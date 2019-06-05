package hu.kalee.multi.tenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantContext {
    private static Logger logger = LoggerFactory.getLogger(TenantContext.class.getName());

    private static final String DEFAULT_TENANT = "tenant_default";

    private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

    static {
        setCurrentTenant(DEFAULT_TENANT);
    }

    public static void setCurrentTenant(String tenant) {
        logger.debug("Setting tenant to " + tenant);
        currentTenant.set(tenant);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.set(null);
    }
}