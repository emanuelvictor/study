package br.org.pti.compras.domain.repository.reports.impl;

import br.org.pti.compras.domain.repository.reports.ICRCReportRepository;
import br.org.pti.compras.infrastructure.report.IReportManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class ICRCReportRepositoryImpl implements ICRCReportRepository {

    /**
     *
     */
    private static final String CRC_REPORT_PATH = "/reports/crc/crc.jasper";

    /**
     *
     */
    private final IReportManager reportManager;

    /**
     * @return ByteArrayOutputStream
     */
    @Override
    public ByteArrayOutputStream exportCRC(final long id, final String documento, final String tipoDocumento) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("id", id);
        parameters.put("documento", documento);
        parameters.put("tipoDocumento", tipoDocumento);

        try {
            final List<ByteArrayInputStream> list = new ArrayList<>();
            final ByteArrayInputStream logo = new ByteArrayInputStream(IOUtils.toByteArray(getClass().getResource("/reports/crc/logo-pti.png")));
            list.add(logo);
            parameters.put("logo", list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.reportManager.exportToPDF(parameters, CRC_REPORT_PATH);
    }
}
