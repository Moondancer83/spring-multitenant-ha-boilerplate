package hu.kalee.multitenant.config.cloud;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.kalee.multitenant.tenant.TenantController;

@ConditionalOnClass(RefreshScope.class)
@RefreshScope
@RestController
@RequestMapping("/tenants")
public class CloudConfigTenantController extends TenantController {
}
