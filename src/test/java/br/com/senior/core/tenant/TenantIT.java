package br.com.senior.core.tenant;

import br.com.senior.core.BaseIT;
import br.com.senior.core.base.ServiceException;
import br.com.senior.core.tenant.pojos.TenantOutput;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Exemplos de código do {@link br.com.senior.core.notification.NotificationClient NotificationClient}
 */
public class TenantIT extends BaseIT {

    private static String token;
    private static TenantClient client;

    @BeforeClass
    public static void beforeClass() throws ServiceException {
        token = login().getJsonToken().getAccess_token();
        client = new TenantClient(token);
    }

    @Test
    public void testGetTenantByDomain() throws ServiceException {
        TenantOutput tenantByDomain = client.getTenantByDomain(System.getenv("TENANT_DOMAIN"));
        Assert.assertNotNull(tenantByDomain);
    }

    @Test
    public void testGetTenantByName() throws ServiceException {
        TenantOutput tenantByName = client.getTenantByName(System.getenv("TENANT_NAME"));
        Assert.assertNotNull(tenantByName);
    }
}