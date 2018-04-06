package tk.cryptonate.backend.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tk.cryptonate.backend.context.CryptonateProperties
import tk.cryptonate.backend.dtos.StatDto
import tk.cryptonate.backend.entities.Wallet
import java.io.BufferedReader
import java.io.FileReader
import java.io.FileWriter
import java.util.regex.Pattern
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/stats")
class StatsController {

    @Autowired
    private lateinit var properties: CryptonateProperties

    @GetMapping
    fun getStats(): ResponseEntity<Any> {
        val reader = BufferedReader(FileReader(properties.csv))
        val wallets = mutableListOf<Wallet>()
        var curLine = reader.readLine()
        while (curLine != null) {
            val curWallet = mapWallet(curLine)
            wallets.add(curWallet)
            curLine = reader.readLine()
        }
        reader.close()
        return ResponseEntity.ok(wallets)
    }

    @PostMapping()
    fun addStats(@Valid @RequestBody request: StatDto): ResponseEntity<Any> {
        val reader = BufferedReader(FileReader(properties.csv))
        val wallets = mutableListOf<Wallet>()
        var curLine = reader.readLine()
        var updated = false
        while (curLine != null) {
            val curWallet = mapWallet(curLine)
            if(curWallet.address == request.address) {
                curWallet.numberOfHashes += request.numberOfHashes!!
                updated = true
            }
            wallets.add(curWallet)
            curLine = reader.readLine()
        }
        if(!updated) {
            wallets.add(Wallet(request.address, request.numberOfHashes!!))
        }
        reader.close()
        updateCSV(wallets)
        return ResponseEntity.ok(mapOf(
                "status" to "ok"
        ))
    }

    private fun mapWallet(line: String): Wallet {
        val p = Pattern.compile("^(.+),(\\d+)$")
        val m = p.matcher(line)
        if (!m.matches()) {
            throw IllegalStateException("The CSV file contains invalid data")
        }
        return Wallet(m.group(1), m.group(2).toLong())
    }

    private fun updateCSV(wallets: List<Wallet>) {
        val writer = FileWriter(properties.csv)
        wallets.forEach {
            writer.append("${it.address},${it.numberOfHashes}\n")
        }
        writer.flush()
        writer.close()
    }

}
