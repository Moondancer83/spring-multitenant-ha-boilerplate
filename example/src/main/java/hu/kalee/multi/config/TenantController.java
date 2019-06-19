package hu.kalee.multi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
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
