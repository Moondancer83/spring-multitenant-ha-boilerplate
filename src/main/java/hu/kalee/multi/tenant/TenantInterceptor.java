package hu.kalee.multi.tenant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TenantInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(TenantInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String serverName = request.getServerName();
        final String[] domains = serverName.split("\\.");
        String tenantId;

        if (domains.length > 1) {
            tenantId = domains[0];
        } else {
            tenantId = "default";
        }

        LOG.info("Retrieving tenantId from request: {}", tenantId);

        TenantContext.setCurrentTenant(tenantId);

        return true;
    }
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        LOG.info("Removing tenantId from context.");
        TenantContext.clear();
    }
}