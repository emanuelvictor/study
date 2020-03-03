package br.org.pti.api.functional.inventario.application.resource;

/**
 * Armazena as permissões do sistema
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
public class Roles {

    private static final String API = "/api/";

    /**
     * Grupos de acesso
     */
    private static final String GRUPO_ACESSO_MAPPING = "grupos-acesso";
    static final String GRUPO_ACESSO_MAPPING_RESOURCE = API + GRUPO_ACESSO_MAPPING;
    static final String GRUPO_ACESSO_POST_ROLE = GRUPO_ACESSO_MAPPING + "/post";
    static final String GRUPO_ACESSO_PUT_ROLE = GRUPO_ACESSO_MAPPING + "/put";
    static final String GRUPO_ACESSO_GET_ROLE = GRUPO_ACESSO_MAPPING + "/get";
    static final String GRUPO_ACESSO_DELETE_ROLE = GRUPO_ACESSO_MAPPING + "/delete";
    /**
     * Usuários
     */
    private static final String USUARIO_MAPPING = "usuarios";
    static final String USUARIO_MAPPING_RESOURCE = API + USUARIO_MAPPING;
    static final String USUARIO_POST_ROLE = USUARIO_MAPPING + "/post";
    static final String USUARIO_PUT_ROLE = USUARIO_MAPPING + "/put";
    static final String USUARIO_GET_ROLE = USUARIO_MAPPING + "/get";

    /**
     * Root
     */
    public static final String ROOT = "root";
    static final String USUARIO_PUT_ACTIVATE_ROLE = USUARIO_PUT_ROLE + "/activate";
    static final String USUARIO_PUT_CHANGE_PASSWORD_ROLE = USUARIO_PUT_ROLE + "/change-password";

    /**
     * Países
     */
    private static final String ENDERECO_MAPPING = "enderecos";
    static final String ENDERECO_MAPPING_RESOURCE = API + ENDERECO_MAPPING;

    /**
     * Permissões
     */
    private static final String PERMISSAO_MAPPING = "permissoes";
    static final String PERMISSAO_MAPPING_RESOURCE = "/api/" + PERMISSAO_MAPPING;
    static final String PERMISSAO_GET_ROLE = PERMISSAO_MAPPING + "/get";

    /**
     * Inventários
     */
    public static final String INVENTARIO_MAPPING = "inventarios";
    static final String INVENTARIO_MAPPING_RESOURCE = API + INVENTARIO_MAPPING;
    public static final String INVENTARIO_POST_ROLE = INVENTARIO_MAPPING + "/post";
    public static final String INVENTARIO_PUT_ROLE = INVENTARIO_MAPPING + "/put";
    public static final String INVENTARIO_GET_ROLE = INVENTARIO_MAPPING + "/get";
    public static final String INVENTARIO_EXECUTE_ROLE = INVENTARIO_MAPPING + "/execute";
    static final String INVENTARIO_DELETE_ROLE = INVENTARIO_MAPPING + "/delete";

    /**
     * Centros de Custo
     */
    private static final String CENTRO_CUSTO_MAPPING = "centros-custo";
    static final String CENTRO_CUSTO_MAPPING_RESOURCE = API + CENTRO_CUSTO_MAPPING;
    public static final String CENTRO_CUSTO_GET_ROLE = CENTRO_CUSTO_MAPPING + "/get";

    /**
     * Centros de Custo
     */
    private static final String PATRIMONIO_MAPPING = "patrimonios";
    static final String PATRIMONIO_MAPPING_RESOURCE = API + PATRIMONIO_MAPPING;

    /**
     * Centros de Custo de Inventário
     */
    private static final String CENTRO_CUSTO_INVENTARIO_MAPPING = "centros-custo-inventario";
    static final String CENTRO_CUSTO_INVENTARIO_MAPPING_RESOURCE = API + CENTRO_CUSTO_INVENTARIO_MAPPING;

}
