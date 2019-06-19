package hu.kalee.multitenant.tenant.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyFileResolver implements ConfigResolver {
    private static final Logger LOG = LoggerFactory.getLogger(PropertyFileResolver.class);

    @Autowired
    private TenantInfo tenantInfo;

    @Override
    public TenantInfo getTenantInfo() {
        return tenantInfo;
    }
}
