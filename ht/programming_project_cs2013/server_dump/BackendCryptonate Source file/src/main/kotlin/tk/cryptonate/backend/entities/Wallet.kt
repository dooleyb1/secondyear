package tk.cryptonate.backend.entities

data class Wallet(
        val address: String,
        var numberOfHashes: Long = 0
)