package org.enterpriseflowsrepository.api.traces.quarkus;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(
        title = "Enterprise Flows Repository - Trace API",
        version = "1.0.0",
        description = "Mediation part for EFR.",
        contact = @Contact(
            name = "Emmanuel LESNE",
            url = "https://github.com/EnterpriseFlowsRepository",
            email = "emmanuel.lesne@middleware-solutions.fr"),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class QuarkusApp extends Application {
    //
}
