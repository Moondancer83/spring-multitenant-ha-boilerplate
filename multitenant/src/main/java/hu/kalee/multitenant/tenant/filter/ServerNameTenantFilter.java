package hu.kalee.multitenant.tenant.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import hu.kalee.multitenant.tenant.TenantContext;
import hu.kalee.multitenant.tenant.extractor.TenantExtractor;

@Component("tenantFilter")
@ConditionalOnProperty(
        name = "multitenant.tenant-id-source",
        havingValue = "servername")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServerNameTenantFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(ServerNameTenantFilter.class);

    public ServerNameTenantFilter() {
        LOG.info("Tenant ID filter: {}", ServerNameTenantFilter.class.getSimpleName());
    }

    @Autowired
    private TenantExtractor tenantExtractor;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        String tenantId = tenantExtractor.getTenantId(request);

        TenantContext.setCurrentTenant(tenantId);

        filterChain.doFilter(request, response);
    }

}