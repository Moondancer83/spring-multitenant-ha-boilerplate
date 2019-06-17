package hu.kalee.multitenant.tenant.extractor;

public interface TenantExtractor {
    String getTenantId(Object input);
}
