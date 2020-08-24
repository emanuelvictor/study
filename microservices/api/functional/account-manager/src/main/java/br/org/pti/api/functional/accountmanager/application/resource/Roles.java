package br.org.pti.api.functional.accountmanager.application.resource;

/**
 * Armazena as permissões do sistema
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
public class Roles {

    private static final String API = "/";

    /**
     * Grupos de acesso
     */
    private static final String ACCESS_GROUP_MAPPING = "access-groups";
    static final String ACCESS_GROUP_MAPPING_RESOURCE = API + ACCESS_GROUP_MAPPING;
    static final String ACCESS_GROUP_POST_ROLE = ACCESS_GROUP_MAPPING + "/post";
    static final String ACCESS_GROUP_PUT_ROLE = ACCESS_GROUP_MAPPING + "/put";
    static final String ACCESS_GROUP_GET_ROLE = ACCESS_GROUP_MAPPING + "/get";
    static final String ACCESS_GROUP_DELETE_ROLE = ACCESS_GROUP_MAPPING + "/delete";
    /**
     * Usuários
     */
    private static final String USER_MAPPING = "users";
    static final String USER_MAPPING_RESOURCE = API + USER_MAPPING;
    static final String USER_POST_ROLE = USER_MAPPING + "/post";
    static final String USER_PUT_ROLE = USER_MAPPING + "/put";
    static final String USER_GET_ROLE = USER_MAPPING + "/get";

    /**
     * Root
     */
    public static final String ROOT = "root";
    static final String USER_PUT_ACTIVATE_ROLE = USER_PUT_ROLE + "/activate";
    static final String USER_PUT_CHANGE_PASSWORD_ROLE = USER_PUT_ROLE + "/change-password";

    /**
     * Permissões
     */
    private static final String PERMISSION_MAPPING = "permissions";
    static final String PERMISSION_MAPPING_RESOURCE = API + PERMISSION_MAPPING;
    static final String PERMISSION_GET_ROLE = PERMISSION_MAPPING + "/get";

}
