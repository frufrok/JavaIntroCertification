package Program;
import java.util.Objects;
import java.util.Random;

public class MyUtils {
    public static Integer getRandom(Integer[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    public static String[] getRandom(String[] array1, String[] array2) {
        int rnd = new Random().nextInt(Math.min(array1.length, array2.length));
        return new String[] {array1[rnd], array2[rnd]};
    }
    public static Double getRandom(Double[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    public static Resolution getRandom(Resolution[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    public static Display getRandom(Display[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    public static Object getRandom(Object[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public static String getReqLength(String str, int length) {
        if (str.length() > length) {
            return str.substring(length);
        }
        else {
            return str + " ".repeat(length - str.length());
        }
    }
}
