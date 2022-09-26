package org.lowc1012.functions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerFunction {
    /**
     * This function listens at endpoint "/api/SendEvent". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/SendEvent
     * 2. curl "{your host}/api/SendEvent?msg={content}"
     */
    @FunctionName("SendEvent")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("HTTP trigger processed a request.");

        // Parse query parameter
        final String query = request.getQueryParameters().get("msg");
        final String msg = request.getBody().orElse(query);

        if (msg == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a msg on the query string or in the request body").build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body(msg).build();
        }
    }
}
