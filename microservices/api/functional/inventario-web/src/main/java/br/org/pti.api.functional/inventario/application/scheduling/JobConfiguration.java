package br.org.pti.inventario.application.scheduling;

import br.org.pti.inventario.domain.entity.patrimonio.Patrimonio;
import br.org.pti.inventario.domain.entity.patrimonio.dto.PatrimonioDTO;
import br.org.pti.inventario.domain.entity.patrimonio.inventario.CentroCustoInventario;
import br.org.pti.inventario.domain.entity.patrimonio.inventario.CentroCustoInventarioStatus;
import br.org.pti.inventario.domain.repository.ICentroCustoInventarioRepository;
import br.org.pti.inventario.domain.repository.IPatrimonioRepository;
import br.org.pti.inventario.domain.repository.feign.IPatrimonioFeignRepository;
import br.org.pti.inventario.domain.service.PatrimonioService;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    /**
     *
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(JobConfiguration.class);

//    /**
//     * @return {JobDetail}
//     */
//    @Bean
//    public JobDetail sincronizarPatrimoniosDoProtheus() {
//        return JobBuilder.newJob()
//                .ofType(SincronizarPatrimoniosDoProtheusJob.class).storeDurably()
//                .withIdentity(SincronizarPatrimoniosDoProtheusJob.class.getName()).build();
//    }
//
//    /**
//     * @param sincronizarPatrimoniosDoProtheus {JobDetail}
//     * @return {Trigger}
//     */
//    @Bean
//    public Trigger triggerSincronizarPatrimoniosDoProtheus(final JobDetail sincronizarPatrimoniosDoProtheus) {
//        return TriggerBuilder.newTrigger()
//                // Inicia agora
//                .startNow()
//                .forJob(sincronizarPatrimoniosDoProtheus)
//                .withIdentity(SincronizarPatrimoniosDoProtheusJob.class.getName())
//                .withSchedule(simpleSchedule()
//                        // Repete á cada 30 minutos
//                        .repeatForever().withIntervalInMinutes(30))
//                .build();
//    }
//
//    /**
//     *
//     */
//    @Component
//    @RequiredArgsConstructor
//    public static class SincronizarPatrimoniosDoProtheusJob implements Job {
//
//        /**
//         *
//         */
//        private final PatrimonioService patrimonioService;
//
//        /**
//         *
//         */
//        private final IPatrimonioRepository patrimonioRepository;
//
//        /**
//         *
//         */
//        private final IPatrimonioFeignRepository patrimonioFeignRepository;
//
//        /**
//         *
//         */
//        private final ICentroCustoInventarioRepository centroCustoInventarioRepository;
//
//        /**
//         * @param context
//         */
//        public void execute(final JobExecutionContext context) {
//
//            LOGGER.info("Iniciando JOB de sincronização dos patrimônios do protheus!");
//
//            // Recupero todos os patrimonios do protheus deste centro de custo
//            final List<PatrimonioDTO> todosOsPatrimoniosNoPodreus = new ArrayList<>();
//
//            final List<CentroCustoInventario> todosOsCentrosDeCustoInventario = centroCustoInventarioRepository.findAll();
//
//            todosOsCentrosDeCustoInventario.forEach(centroCustoInventario -> {
//                todosOsPatrimoniosNoPodreus.addAll(this.patrimonioFeignRepository.listByFilters(centroCustoInventario.getCentroCusto().getCodigo(), null, null, new PageRequest(0, 1000000)).getContent());
//            });
//
//            // Recupero todos os centros custos inventários da base de dados
//            todosOsCentrosDeCustoInventario.forEach(centroCustoInventario -> {
//                final List<Patrimonio> patrimoniosNaBaseLocal = this.patrimonioRepository.listAllPatrimoniosByCentroCustoCodigoAndFilters(
//                        null,
//                        null,
//                        null,
//                        centroCustoInventario.getCentroCusto().getCodigo(),
//                        null,
//                        false,
//                        null,
//                        null,
//                        new PageRequest(0, 1000000)).getContent();
//
////                // Recupero todos os patrimonios do protheus deste centro de custo
////                final List<PatrimonioDTO> patrimoniosNoProtheus = this.patrimonioFeignRepository.listByFilters(centroCustoInventario.getCentroCusto().getCodigo(), null, null, new PageRequest(0, 1000000)).getContent();
//
//                patrimoniosNaBaseLocal.forEach(patrimonioLocal -> {
//                    // Se não encontrou essa plaqueta nesse centro de custo
//                    if (todosOsPatrimoniosNoPodreus.stream().noneMatch(patrimonioNoProtheus -> patrimonioLocal.getNumero().equals(patrimonioNoProtheus.getPlaqueta()) && patrimonioNoProtheus.getCentroCusto().getCodigo().equals(patrimonioLocal.getCentroCustoInventario().getCentroCusto().getCodigo()))) {
//
//                        todosOsPatrimoniosNoPodreus.stream().filter(patrimonioNoPodreus -> patrimonioNoPodreus.getPlaqueta().equals(patrimonioLocal.getNumero())).findFirst().ifPresentOrElse(patrimonioDTONoProtheus -> {
//
//                                    if (!patrimonioDTONoProtheus.getCentroCusto().getCodigo().equals(patrimonioLocal.getCentroCustoInventario().getCentroCusto().getCodigo())) {
//                                        final CentroCustoInventario toCentroCustoInventario = this.centroCustoInventarioRepository.findByInventarioIdAndCentroCustoCodigo(centroCustoInventario.getInventario().getId(), patrimonioDTONoProtheus.getCentroCusto().getCodigo());  // Se não tem o centor de custo cadastrado localmente deleta
//
//                                        if (toCentroCustoInventario != null) {
//                                            patrimonioLocal.setCentroCustoInventario(toCentroCustoInventario);
//
//                                            this.patrimonioRepository.save(patrimonioLocal);
//                                        } else
//                                            this.patrimonioRepository.deleteById(patrimonioLocal.getId());
//                                    }
//
//                                }, () ->
//                                        this.patrimonioRepository.deleteById(patrimonioLocal.getId())
//                        );
//
//                    }
//                });
//            });
//
//        }
//    }


    /**
     * @return {JobDetail}
     */
    @Bean
    public JobDetail verificarInventarioVencido() {
        return JobBuilder.newJob()
                .ofType(LocalJob.class).storeDurably()
                .withIdentity(LocalJob.class.getName()).build();
    }

    /**
     * @param verificarInventarioVencido {JobDetail}
     * @return {Trigger}
     */
    @Bean
    public Trigger triggerVerificarInventarioVencido(final JobDetail verificarInventarioVencido) {
        return TriggerBuilder.newTrigger()
                // // Inicia a meia noite
                .startAt(DateBuilder.evenHourDate(getMeiaNoite()))
//                .startNow()
                .forJob(verificarInventarioVencido)
                .withIdentity(LocalJob.class.getName())
                .withSchedule(simpleSchedule()
//                        .repeatForever().withIntervalInHours(999999999))
                        .repeatForever().withIntervalInHours(24))
                .build();
    }

    /**
     *
     */
    @Component
    @RequiredArgsConstructor
    public static class LocalJob implements Job {

        /**
         *
         */
        private final PatrimonioService patrimonioService;

        /**
         *
         */
        private final IPatrimonioRepository patrimonioRepository;

        /**
         *
         */
        private final IPatrimonioFeignRepository patrimonioFeignRepository;

        /**
         *
         */
        private final ICentroCustoInventarioRepository centroCustoInventarioRepository;

        /**
         * @param context
         */
        public void execute(final JobExecutionContext context) {

            LOGGER.info("Iniciando JOB de migração de patrimônios não inventariados para não encontrados!");

            final List<CentroCustoInventario> vencidos = centroCustoInventarioRepository.listVencidos(LocalDate.now());

            vencidos.forEach(centroCustoInventario -> {

                if (centroCustoInventario.getDataTerminoExtendida() == null || centroCustoInventario.getDataTerminoExtendida().isBefore(LocalDate.now())) {

                    final List<PatrimonioDTO> patrimonios = patrimonioFeignRepository.listByFilters(centroCustoInventario.getCentroCusto().getCodigo(), null, null, new PageRequest(0, 1000000)).getContent();

                    patrimonios.forEach(patrimonioDTO -> {
                        if (patrimonioRepository.findByCodigoBaseAndItemAndNumero(patrimonioDTO.getCodigoBase(), patrimonioDTO.getItem(), patrimonioDTO.getPlaqueta()).isEmpty()) {

                            final Patrimonio patrimonio = new Patrimonio();

                            patrimonio.setCentroCustoInventario(centroCustoInventario);
                            patrimonio.setEncontrado(false);

                            patrimonio.setLocalizacao(patrimonioDTO.getLocalizacao());
                            patrimonio.setSobraFisica(false);
                            patrimonio.setDescricao(patrimonioDTO.getDescricao());
                            patrimonio.setCodigoBase(patrimonioDTO.getCodigoBase());
                            patrimonio.setNumero(patrimonioDTO.getPlaqueta());
                            patrimonio.setNumeroSerie(patrimonioDTO.getNumeroSerie());
                            patrimonio.setCapacidade(patrimonioDTO.getCapacidade());
                            patrimonio.setComplemento(patrimonioDTO.getComplemento());
                            patrimonio.setDepartamento(patrimonioDTO.getDepartamento());
                            patrimonio.setItem(patrimonioDTO.getItem());
                            patrimonio.setMarca(patrimonioDTO.getMarca());
                            patrimonio.setModelo(patrimonioDTO.getModelo());
                            patrimonio.setObservacao(patrimonioDTO.getObservacao());

                            this.patrimonioService.encontrarFromJob(patrimonio);
                        }
                    });

                    centroCustoInventario.setStatus(CentroCustoInventarioStatus.APROVADO);

                    centroCustoInventarioRepository.save(centroCustoInventario);

                }

            });

        }
    }

    /**
     * Pega a data corrente com a data setada para meia noite
     *
     * @return Date
     */
    private static Date getMeiaNoite() {
        final Calendar meiaNoite = Calendar.getInstance();
        meiaNoite.set(meiaNoite.get(Calendar.YEAR),
                meiaNoite.get(Calendar.MONTH),
                meiaNoite.get(Calendar.DAY_OF_MONTH),
                23,
                59,
                59);
        return meiaNoite.getTime();
    }
}
