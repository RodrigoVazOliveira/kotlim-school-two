package br.dev.rvz.forum.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class SendMailService(
    private val javaMailSender: JavaMailSender
) {
    private val logger: Logger = LoggerFactory.getLogger(SendMailService::class.java)

    fun notifyMail(email: String) {
        val simpleMailMessage = SimpleMailMessage()
        simpleMailMessage.setSubject("[TOPICO] RESPOSTA RECEBIDA")
        simpleMailMessage.setText("Ola, seu topico foi respondido.")
        simpleMailMessage.setTo(email)

        logger.info("enviando email para - $email")
        javaMailSender.send(simpleMailMessage)
    }
}