package br.com.senior.core.authentication.pojos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Payload de entrada do {@link br.com.senior.core.authentication.AuthenticationClient#loginWithKey(LoginWithKeyInput) loginWithKey}
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class LoginWithKeyInput {

    /**
     * Chave da aplicação
     */
    String accessKey;
    /**
     * Segredo da chave informada
     */
    String secret;
    /**
     * Nome do tenant em que se deseja efetuar o login
     */
    String tenantName;
    /**
     * Escopo do token da autenticação. Opcional, se não for informado será usado o valor padrão 'desktop'
     */
    String scope;

    /**
     * Construtor
     *
     * @param accessKey  - Chave de autenticação
     * @param secret     - Senha de autenticação
     * @param tenantName - Nome do tenant
     */
    public LoginWithKeyInput(String accessKey, String secret, String tenantName) {
        this.accessKey = accessKey;
        this.secret = secret;
        this.tenantName = tenantName;
    }

}
