package br.com.senior.core.user.pojos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * Payload de saída do {@link br.com.senior.core.user.UserClient#createUser(CreateUserInput) createUser}
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserOutput {

    /**
     * Nome do usuário recém criado
     */
    public String username;
}