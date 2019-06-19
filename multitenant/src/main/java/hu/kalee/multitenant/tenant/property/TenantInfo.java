package hu.kalee.multitenant.tenant.property;

import java.util.ArrayList;
import java.util.List;

public class TenantInfo {
    private List<Tenant> tenants = new ArrayList<>();

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(final List<Tenant> tenants) {
        this.tenants = tenants;
    }

    public static class Tenant {
        String name;
        Datasource datasource;

        public Tenant() {
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }


        public Datasource getDatasource() {
            return datasource;
        }

        public void setDatasource(final Datasource datasource) {
            this.datasource = datasource;
        }

        public static class Datasource {
            String url;
            String username;
            String password;

            public String getUrl() {
                return url;
            }

            public void setUrl(final String url) {
                this.url = url;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(final String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(final String password) {
                this.password = password;
            }
        }
    }
}
