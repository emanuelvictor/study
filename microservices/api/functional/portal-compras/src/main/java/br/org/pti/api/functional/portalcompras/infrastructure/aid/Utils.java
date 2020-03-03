package br.org.pti.api.functional.portalcompras.infrastructure.aid;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public final class Utils {

    /**
     * Transforma uma array em uma lista, sem estourar exceção se o array for nullo ou vazio
     */
    public static <T> List<T> getListFromArray(T[] array) {
        if (array == null || array.length == 0)
            return null;
        return Arrays.asList(array);
    }

    /**
     * A palavra nocache pode ser utilizada para requisições de imagem, assim o navegador não armazena o cache.
     *
     * @param schema {String}
     * @return {String}
     */
    public static String removeNoCache(final String schema) {
        if (schema.contains("?nocache"))
            return schema.replace(schema.substring(schema.indexOf("?nocache")), "");
        return schema;
    }

    /**
     * Remove as acentuações
     *
     * @param str {String}
     * @return {String}
     */
    public static String normalizeSymbolsAndAccents(final String str) {
        final String nfdNormalizedString = Normalizer.normalize(org.apache.commons.lang.StringUtils.defaultString(str), Normalizer.Form.NFD);
        final Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
