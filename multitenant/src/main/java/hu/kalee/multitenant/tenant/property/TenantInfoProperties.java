package hu.kalee.multitenant.tenant.property;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingClass("org.springframework.cloud.context.config.annotation.RefreshScope")
@ConfigurationProperties(prefix = "multitenant")
public class TenantInfoProperties extends TenantInfo {

}
