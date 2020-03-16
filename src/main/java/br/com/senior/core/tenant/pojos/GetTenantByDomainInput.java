package br.com.senior.core.tenant.pojos;

import br.com.senior.core.authentication.pojos.LoginInput;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Payload de entrada do {@link br.com.senior.core.tenant.TenantClient#getTenantByDomain(String) getTenantByDomain}
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetTenantByDomainInput {
    /**
     * O domínio do tenant a ter os dados retornados
     */
    @NonNull
    String tenantDomain;
}
