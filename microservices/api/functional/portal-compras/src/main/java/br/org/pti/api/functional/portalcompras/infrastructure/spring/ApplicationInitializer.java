package br.org.pti.api.functional.portalcompras.infrastructure.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 01/10/2019
 */
@Service
@Profile("alpha")
public class ApplicationInitializer {

    private final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

    private List<InitialTask> tasks;

    /**
     *
     *
     * @param tasks
     */
    public ApplicationInitializer(List<InitialTask> tasks) {
        this.tasks = tasks;
    }

    /**
     *
     */
    @PostConstruct
    public void orderTasks() {
        this.tasks.sort(AnnotationAwareOrderComparator.INSTANCE);
        this.logger.info("Ordering tasks");
    }

    /**
     * {@inheritDoc}
     *
     * @param event
     */
    @EventListener
    public void onApplicationStart(ContextRefreshedEvent event) {
        this.logger.info("Running application initializer tasks");
        this.tasks.forEach(InitialTask::perform);
        this.logger.info("{} tasks performed", this.tasks.size());
    }
}
