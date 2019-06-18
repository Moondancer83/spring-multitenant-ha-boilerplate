package hu.kalee.multitenant.tenant.extractor;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import hu.kalee.multitenant.tenant.TenantContext;

@Component
@ConditionalOnProperty(name = "multitenant.tenant-id-source", havingValue = "request")
public class RequestHeaderTenantExtractor implements TenantExtractor {
    private static final Logger LOG = LoggerFactory.getLogger(RequestHeaderTenantExtractor.class);

    public String getTenantId(final Object input) {
        HttpServletRequest request = (HttpServletRequest) input;

        String tenantId = request.getHeader("X-Tenant-ID");
        LOG.info("Retrieving tenantId from request: {}, {}", tenantId, request);

        if (tenantId == null) {
            tenantId = TenantContext.DEFAULT_TENANT;
        }

        return tenantId;
    }
}
