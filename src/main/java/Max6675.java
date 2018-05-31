import piio.SPI;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Max6675 {

    private static SPI spi = null;

    public static void main(String args[]) throws InterruptedException, IOException {
        System.out.println("Starting Thermocouple Application.");
        spi = new SPI();
        while (true) {
            System.out.println(getConversionValue());
            Thread.sleep(2000);
        }
    }

    private static int getData() throws IOException {
        byte txData[] = new byte[]{0, 0, 0, 0};
        byte[] result = spi.write(txData);
        System.out.printf(String.format("0x%02X%02X ", result[0], result[1]));
        return ByteBuffer.wrap(result).getShort();
    }

    public static double getConversionValue() throws IOException {
        int data = getData();
        data = data >> 3;
        System.out.printf("%d ", data);
        return data / 4.0;
    }
}