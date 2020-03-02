package br.org.pti.compras.domain.repository.reports;

import java.io.ByteArrayOutputStream;

public interface ICRCReportRepository {

    ByteArrayOutputStream exportCRC(final long id, final String documento, final String tipoDocumento);

}
