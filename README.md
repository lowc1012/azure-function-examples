# Azure function examples
This repo uses Azure Functions with Java to build an event-based application
which includes two parts:

1. Send events to Event Grid via HTTP requests (HttpTrigger)
2. Process the telemetry data (EventGridTrigger) and store results in a Cosmos DB (CosmosDBOutput).