package org.lowc1012.functions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.EventGridTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

public class EventGridTriggerFunction {
    @FunctionName("eventGridMonitorString")
    public void logEvent(
            @EventGridTrigger(
                    name = "event"
            )
            String content,
            final ExecutionContext context) {
        context.getLogger().info("Event content: " + content);
    }
}
