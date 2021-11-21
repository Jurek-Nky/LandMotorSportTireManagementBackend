package com.dev;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
public class TireManagementConfig {
    @PostConstruct
    void started() {                                        // This changes the Timezone for the whole project.
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));   // That is necessary because sql is an asshole and saves a time-offset with all dates.
}                                                           // This will cause dates that have been serialized to differ from dates that haven't.
}
