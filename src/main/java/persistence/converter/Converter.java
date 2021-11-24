package persistence.converter;

public class Converter {

    public static int byteToInt(byte[] data, int pos) {

        int result = ((int) (data[pos] & 0xff) << 8) |
                     ((int) data[pos+1] & 0xff);

        return result;

    }












}
