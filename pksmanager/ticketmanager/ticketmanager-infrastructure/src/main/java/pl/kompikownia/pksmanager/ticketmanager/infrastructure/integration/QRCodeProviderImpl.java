package pl.kompikownia.pksmanager.ticketmanager.infrastructure.integration;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Component;
import pl.kompikownia.pksmanager.ticketmanager.business.integration.QRCodeProvider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class QRCodeProviderImpl implements QRCodeProvider {

    @Override
    public byte[] generateQrCode(String ticketId) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(ticketId, BarcodeFormat.QR_CODE, 320, 240);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return outputStream.toByteArray();
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
