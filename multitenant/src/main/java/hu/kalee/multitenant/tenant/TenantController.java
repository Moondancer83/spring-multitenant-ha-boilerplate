package hu.kalee.multitenant.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.kalee.multitenant.tenant.property.TenantInfo;

@ConditionalOnMissingClass("org.springframework.cloud.context.config.annotation.RefreshScope")
@RestController
@RequestMapping("/tenants")
public class TenantController {
    @Autowired
    private TenantInfo tenantInfoList;

    @GetMapping
    public TenantInfo getTenantConfigs() {
        return tenantInfoList;
    }
}
