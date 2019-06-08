package hu.kalee.multi.tenant;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TenantFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(TenantFilter.class);

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        String tenantId = getTenantId(request);

        TenantContext.setCurrentTenant(tenantId);

        filterChain.doFilter(request, response);
    }

    private String getTenantId(final HttpServletRequest request) {
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