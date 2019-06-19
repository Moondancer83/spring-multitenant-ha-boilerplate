package hu.kalee.multitenant.config.cloud;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import hu.kalee.multitenant.tenant.property.TenantInfo;

@ConditionalOnClass(RefreshScope.class)
@RefreshScope(proxyMode = ScopedProxyMode.NO)
@Component
@ConfigurationProperties(prefix = "multi")
public class TenantInfoCloudProperty extends TenantInfo {

}
