package br.org.pti.api.functional.accountmanager.application.resource;

/**
 * Armazena as permissões do sistema
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
public class Roles {

    private static final String SEPARATOR = "/";

    private static final String ROOT = "root";

    private static final String APP_IDENTIFIER = "account-manager";

    /**
     * Grupos de acesso
     */
    private static final String ACCESS_GROUP_MAPPING = "access-groups";
    static final String ACCESS_GROUP_MAPPING_RESOURCE = SEPARATOR + ACCESS_GROUP_MAPPING;

    private static final String BASE_ACCESS_GROUP_ROLE = ROOT + SEPARATOR + APP_IDENTIFIER + SEPARATOR + ACCESS_GROUP_MAPPING;
    static final String ACCESS_GROUP_POST_ROLE = BASE_ACCESS_GROUP_ROLE + SEPARATOR + "post";
    static final String ACCESS_GROUP_PUT_ROLE = BASE_ACCESS_GROUP_ROLE + SEPARATOR + "put";
    static final String ACCESS_GROUP_GET_ROLE = BASE_ACCESS_GROUP_ROLE + SEPARATOR + "get";
    static final String ACCESS_GROUP_DELETE_ROLE = BASE_ACCESS_GROUP_ROLE + SEPARATOR + "delete";

    /**
     * Usuários
     */
    private static final String USER_MAPPING = "users";
    static final String USER_MAPPING_RESOURCE = SEPARATOR + USER_MAPPING;

    private static final String BASE_USER_ROLE = ROOT + SEPARATOR + APP_IDENTIFIER + SEPARATOR + USER_MAPPING;
    static final String USER_POST_ROLE = BASE_USER_ROLE + SEPARATOR + "post";
    static final String USER_PUT_ROLE = BASE_USER_ROLE + SEPARATOR + "put";
    static final String USER_GET_ROLE = BASE_USER_ROLE + SEPARATOR + "get";
    static final String USER_PUT_ACTIVATE_ROLE = USER_PUT_ROLE + SEPARATOR + "activate";
    static final String USER_PUT_CHANGE_PASSWORD_ROLE = USER_PUT_ROLE + SEPARATOR + "change-password";

    /**
     * Permissões
     */
    private static final String PERMISSION_MAPPING = "permissions";
    static final String PERMISSION_MAPPING_RESOURCE = SEPARATOR + PERMISSION_MAPPING;

    private static final String BASE_PERMISSION_ROLE = ROOT + SEPARATOR + APP_IDENTIFIER + SEPARATOR + PERMISSION_MAPPING;
    static final String PERMISSION_GET_ROLE = BASE_PERMISSION_ROLE + SEPARATOR + "get";

}
