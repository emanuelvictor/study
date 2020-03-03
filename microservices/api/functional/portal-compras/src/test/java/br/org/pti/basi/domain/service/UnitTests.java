package br.org.pti.basi.domain.service;

import br.com.caelum.stella.tinytype.CNPJ;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class UnitTests {

    /**
     *
     */
    @Test
    public void extractLojaByCnpj() {
        final String documento = "07307068000167";
        assertEquals(new CNPJ(documento).getNumeroFormatado(), "07.307.068/0001-67");
        assertEquals(new CNPJ(documento).getNumeroFormatado().substring(new CNPJ(documento).getNumeroFormatado().indexOf("/") + 1, new CNPJ(documento).getNumeroFormatado().indexOf("-")), "0001");
		assertEquals(extractLoja(documento), "0001");
    }

	/**
	 * @param documento String
	 * @return String
	 */
	private static String extractLoja(final String documento) {
		return new CNPJ(documento).getNumeroFormatado().substring(new CNPJ(documento).getNumeroFormatado().indexOf("/") + 1, new CNPJ(documento).getNumeroFormatado().indexOf("-"));
	}


}
