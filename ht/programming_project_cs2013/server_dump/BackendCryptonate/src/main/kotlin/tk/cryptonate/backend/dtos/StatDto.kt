package tk.cryptonate.backend.dtos

import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.NotNull

data class StatDto(
        @field:[NotBlank]
        val address: String = "",

        @field:[NotNull]
        var numberOfHashes: Long? = null
)