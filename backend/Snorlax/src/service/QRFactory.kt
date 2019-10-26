package com.yt8492.service

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.yt8492.UUIDHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import javax.imageio.ImageIO

object QRFactory {
    suspend fun createQR(text: String): File = withContext(Dispatchers.IO) {
        val hints = ConcurrentHashMap<EncodeHintType, Any>()
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.M
        hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
        hints[EncodeHintType.MARGIN] = 1
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 100, 100, hints)
        val image = MatrixToImageWriter.toBufferedImage(bitMatrix)
        val file = File(UUIDHelper.createUUID() + ".png")
        ImageIO.write(image, "png", file)
        file
    }
}