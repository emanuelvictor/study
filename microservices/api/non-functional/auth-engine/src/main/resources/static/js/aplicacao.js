"use strict";

/**
 *
 */
function buscarAplicacoes() {

    const filter = $('#inFilter').val();

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/seguro/aplicacoes/buscar?filtro=" + filter,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#listaAplicacoes').replaceWith(data);
        },
        error: function (e) {
            console.log(e);
        }
    });
}

/**
 *
 * @param id
 */
function excluirAplicacao(id) {
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/seguro/aplicacoes/" + id,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (data) {
            window.location = "/seguro/aplicacoes";
        },
        error: function (e) {
            console.log(e);
        }
    });
}


function salvarAplicacao(aplicacao) {

    console.log(aplicacao);

}

/**
 *
 * @param id
 */
function mudarSenha(id) {
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/seguro/aplicacoes/mudar-senha",
        data: JSON.stringify(id),
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#inNovaSenha').replaceWith(data);
            $('#novaSenhaDialog').modal('show');
        },
        error: function (e) {
            console.log(e);
        }
    });
}
