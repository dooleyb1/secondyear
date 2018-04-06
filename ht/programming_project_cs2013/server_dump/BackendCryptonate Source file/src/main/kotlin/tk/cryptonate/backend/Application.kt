package tk.cryptonate.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
open class Application

fun main(args: Array<String>) {
    SpringApplicationBuilder()
            .sources(Application::class.java)
            .web(true)
            .build().run(*args)
}