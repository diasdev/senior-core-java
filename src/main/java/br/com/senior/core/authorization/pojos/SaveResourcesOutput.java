package br.com.senior.core.authorization.pojos;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * Payload de saída do {@link br.com.senior.core.authorization.AuthorizationClient#saveResources(SaveResourcesInput) saveResources}
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveResourcesOutput {

    /**
     * Retorna os dados dos recursos criados
     */
    List<Resource> resources;

}
