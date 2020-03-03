package br.org.pti.api.functional.portalcompras.application.resource;

/**
 * Armazena as permissões do sistema
 */
public class Roles {

    private static final String API = "/api/";

    /**
     * Administrador
     */
    public static final String ADMINISTRADOR = "administrador";

    /**
     * Categorias
     */
    private static final String CATEGORIA_MAPPING = "categorias";
//    static final String BANCO_GET_ROLE = BANCO_MAPPING + "/get";
    static final String CATEGORIA_MAPPING_RESOURCE = API + CATEGORIA_MAPPING;
    static final String CATEGORIA_POST_ROLE = CATEGORIA_MAPPING + "/post";
    static final String CATEGORIA_PUT_ROLE = CATEGORIA_MAPPING + "/put";
//    static final String CATEGORIA_GET_ROLE = CATEGORIA_MAPPING + "/get";
    static final String CATEGORIA_PUT_ACTIVATE_ROLE = CATEGORIA_PUT_ROLE + "/activate";

    /**
     * Banco
     */
    private static final String BANCO_MAPPING = "bancos";
    static final String BANCO_MAPPING_RESOURCE = API + BANCO_MAPPING;
//    static final String BANCO_GET_ROLE = BANCO_MAPPING + "/get";

    /**
     * Tipos de documento
     */
    private static final String TIPO_DOCUMENTO_MAPPING = "tipos-documentos";
    static final String TIPO_DOCUMENTO_MAPPING_RESOURCE = API + TIPO_DOCUMENTO_MAPPING;
    static final String TIPO_DOCUMENTO_POST_ROLE = TIPO_DOCUMENTO_MAPPING + "/post";
    static final String TIPO_DOCUMENTO_PUT_ROLE = TIPO_DOCUMENTO_MAPPING + "/put";
    static final String TIPO_DOCUMENTO_GET_ROLE = TIPO_DOCUMENTO_MAPPING + "/get";
    static final String TIPO_DOCUMENTO_PUT_ACTIVATE_ROLE = TIPO_DOCUMENTO_PUT_ROLE + "/activate";

    /**
     * Tipos de cadastro
     */
    private static final String TIPO_CADASTRO_MAPPING = "tipos-cadastros";
    static final String TIPO_CADASTRO_MAPPING_RESOURCE = API + TIPO_CADASTRO_MAPPING;
//    static final String TIPO_CADASTRO_GET_ROLE = TIPO_CADASTRO_MAPPING + "/get";
    static final String TIPO_CADASTRO_POST_ROLE = TIPO_CADASTRO_MAPPING + "/post";
    static final String TIPO_CADASTRO_PUT_ROLE = TIPO_CADASTRO_MAPPING + "/put";
    static final String TIPO_CADASTRO_PUT_ACTIVATE_ROLE = TIPO_CADASTRO_PUT_ROLE + "/activate";

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
    static final String USUARIO_PUT_ACTIVATE_ROLE = USUARIO_PUT_ROLE + "/activate";
    static final String USUARIO_PUT_CHANGE_PASSWORD_ROLE = USUARIO_PUT_ROLE + "/change-password";
    static final String USUARIO_GET_ROLE = USUARIO_MAPPING + "/get";

    /**
     * Fornecedores
     */
    private static final String FORNECEDOR_MAPPING = "fornecedores";
    static final String FORNECEDOR_MAPPING_RESOURCE = API + FORNECEDOR_MAPPING;
    static final String FORNECEDOR_POST_ROLE = FORNECEDOR_MAPPING + "/post";
    public static final String FORNECEDOR_PUT_ROLE = FORNECEDOR_MAPPING + "/put";
//    static final String FORNECEDOR_PUT_ACTIVATE_ROLE = FORNECEDOR_PUT_ROLE + "/activate";
    static final String FORNECEDOR_APPROVE_ACTIVATE_ROLE = FORNECEDOR_PUT_ROLE + "/approve";
//    static final String FORNECEDOR_PUT_CHANGE_PASSWORD_ROLE = FORNECEDOR_PUT_ROLE + "/change-password";
    public static final String FORNECEDOR_GET_ROLE = FORNECEDOR_MAPPING + "/get";

    /**
     * Aviso de contratação
     */
    private static final String AVISO_CONTRATACAO_MAPPING = "avisos-contratacoes";
    static final String AVISO_CONTRATACAO_MAPPING_RESOURCE = API + AVISO_CONTRATACAO_MAPPING;
    static final String AVISO_CONTRATACAO_POST_ROLE = AVISO_CONTRATACAO_MAPPING + "/post";
    static final String AVISO_CONTRATACAO_PUT_ROLE = AVISO_CONTRATACAO_MAPPING + "/put";
//    static final String AVISO_CONTRATACAO_GET_ROLE = AVISO_CONTRATACAO_MAPPING + "/get";

    /**
     * Extrato de contratação
     */
    private static final String EXTRATO_CONTRATO_MAPPING = "extratos-contratos";
    static final String EXTRATO_CONTRATO_MAPPING_RESOURCE = API + EXTRATO_CONTRATO_MAPPING;
    static final String EXTRATO_CONTRATO_POST_ROLE = EXTRATO_CONTRATO_MAPPING + "/post";
    static final String EXTRATO_CONTRATO_PUT_ROLE = EXTRATO_CONTRATO_MAPPING + "/put";
//    static final String EXTRATO_CONTRATO_GET_ROLE = EXTRATO_CONTRATO_MAPPING + "/get";

    /**
     * Avisos de editais
     */
    private static final String AVISO_EDITAL_MAPPING = "avisos-editais";
    static final String AVISO_EDITAL_MAPPING_RESOURCE = API + AVISO_EDITAL_MAPPING;
    static final String AVISO_EDITAL_POST_ROLE = AVISO_EDITAL_MAPPING + "/post";
    static final String AVISO_EDITAL_PUT_ROLE = AVISO_EDITAL_MAPPING + "/put";
//    static final String AVISO_EDITAL_GET_ROLE = AVISO_EDITAL_MAPPING + "/get";

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
//    static final String PERMISSAO_GET_ROLE = PERMISSAO_MAPPING + "/get";

    /**
     * Atividades Econômicas
     */
    private static final String ATIVIDADE_ECONOMICA_MAPPING = "atividades-economicas";
    static final String ATIVIDADE_ECONOMICA_MAPPING_RESOURCE = API + ATIVIDADE_ECONOMICA_MAPPING;

}
