package br.org.pti.api.functional.portalcompras.domain.repository.reports;

import java.io.ByteArrayOutputStream;

public interface ICRCReportRepository {

    ByteArrayOutputStream exportCRC(final long id, final String documento, final String tipoDocumento);

}
