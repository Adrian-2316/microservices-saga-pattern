package com.bosonit.techxperience.orchestration.adapter.rest.controller;


import com.bosonit.techxperience.orchestration.application.ports.OrchestrationPort;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("orchestration")
@ApplicationScoped
public class OrchestrationController {

    @Inject
    OrchestrationPort orchestrationPort;

    @POST
    @Path("/saga")
    @NonBlocking
    public Uni<Void> executeSaga(@QueryParam("failurePercentage") int failurePercentage) {
        log.info("-- Starting saga execution --");
        return orchestrationPort.executeSaga(failurePercentage);
    }
}
