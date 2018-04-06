package tk.cryptonate.backend.context

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.validation.constraints.NotNull

@Configuration
@ConfigurationProperties(prefix = "cryptonate")
open class CryptonateProperties(
        @field:[NotNull]
        var csv: String = ""
)