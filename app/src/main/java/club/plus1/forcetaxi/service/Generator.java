package club.plus1.forcetaxi.service;

import java.text.DecimalFormat;

public class Generator {

    /**
     * Метод получения псевдослучайного пинкода от "0000" до "9999"
     */
    public static String smsCode() {
        int rnd = (int) (Math.random() * 9999);
        DecimalFormat formatter = new DecimalFormat("0000");
        return formatter.format(rnd);
    }

}
