"use strict";

/**
 *
 * @param componente
 * @param tempo
 */
function esconder(componente, tempo) {
    setTimeout(() => {
        $(componente).fadeOut(1000)
    }, tempo)
}