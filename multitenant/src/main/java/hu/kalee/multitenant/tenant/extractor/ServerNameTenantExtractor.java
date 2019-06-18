package hu.kalee.multitenant.tenant.extractor;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import hu.kalee.multitenant.tenant.TenantContext;

@Component
@ConditionalOnProperty(name = "multitenant.tenant-id-source", havingValue = "servername")
public class ServerNameTenantExtractor implements TenantExtractor {
    private static final Logger LOG = LoggerFactory.getLogger(ServerNameTenantExtractor.class);

    public String getTenantId(final Object input) {
        HttpServletRequest request = (HttpServletRequest) input;
        final String serverName = request.getServerName();
        LOG.info("Retrieving tenantId from request: {}, {}", request.getRequestURI(), request);

        final String[] domains = serverName.split("\\.");
        String tenantId;

        if (domains.length > 1) {
            tenantId = domains[0];
        } else {
            tenantId = TenantContext.DEFAULT_TENANT;
        }
        return tenantId;
    }
}
