package readsafefile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;

import org.apache.commons.codec.binary.Base64;

public class DecryptXOR {

    private static final String[] SAVES = {"CCGameManager.dat"};
    private static final int XOR_KEY = 11;

    private static byte[] xor(byte[] data, int key) {
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = (byte) (data[i] ^ key);
        }
        return result;
    }

    private static byte[] decrypt(String data) throws IOException {
        byte[] decodedData = Base64.decodeBase64(data.replace('-', '+').replace('_', '/'));
        ByteArrayInputStream bis = new ByteArrayInputStream(decodedData);
        GZIPInputStream gzipInputStream = new GZIPInputStream(bis);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = gzipInputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        gzipInputStream.close();
        bis.close();
        bos.close();
        return bos.toByteArray();
    }

	System.out.println("hi");

    public static void decryptAndWriteFiles() throws IOException {
        String appDataPath = System.getenv("LOCALAPPDATA") + "\\GeometryDash\\";
        for (String save : SAVES) {
            File inputFile = new File(appDataPath + save);
            if (!inputFile.exists()) {
                System.err.println("Input file not found: " + inputFile.getAbsolutePath());
                continue;
            }
            byte[] encryptedData = new byte[(int) inputFile.length()];
            try (FileInputStream fis = new FileInputStream(inputFile)) {
                fis.read(encryptedData);
            }

            byte[] decryptedData = decrypt(new String(xor(encryptedData, XOR_KEY)));
            File outputFile = new File("C:\\ExtremeDemonList\\userdata\\" + save + ".xml");
            try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)) {
                osw.write(new String(decryptedData, StandardCharsets.UTF_8));
            }
            System.out.println("File decrypted and written: " + outputFile.getAbsolutePath());
        }
    }
}
