package com.jdaniel.sakuappapi.status;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    /**
     * Health check endpoint.
     *
     * @return a simple "Hello, World!" message
     */
    @GetMapping
    public String health() {
        return "Hello, World! \n" +
                "This is a health check endpoint for the SakuApp API.\n" +
                "If you see this message, the API is running.";
    }

}
