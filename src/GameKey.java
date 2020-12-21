import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class GameKey {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private String key;

    public GameKey() {
        this.key = generateKey();
    }

    public String getHashMac(String data) {
        try {
            Mac sha2_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha2_HMAC.init(secret_key);
            return getHex(sha2_HMAC.doFinal(data.getBytes()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return key + data;
        }
    }

    public String getKey() {
        return this.key;
    }

    private static String getHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static String generateKey() {
        StringBuilder stringBuilder = new StringBuilder( 32 );
        for( int i = 0; i < 32; i++ )
            stringBuilder.append( HEX_ARRAY[new SecureRandom().nextInt(HEX_ARRAY.length)] );
        return stringBuilder.toString();
    }
}
