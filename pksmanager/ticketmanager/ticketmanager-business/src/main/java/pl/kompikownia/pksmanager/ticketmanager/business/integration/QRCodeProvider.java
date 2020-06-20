package pl.kompikownia.pksmanager.ticketmanager.business.integration;

public interface QRCodeProvider {
    byte[] generateQrCode(String ticketId);
}
