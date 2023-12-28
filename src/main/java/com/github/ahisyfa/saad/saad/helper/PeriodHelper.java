package com.github.ahisyfa.saad.saad.helper;

import java.time.YearMonth;

/**
 * PeriodHelper
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: PeriodHelper.java, v 0.1 2023-07-22  23.09 Ahmad Isyfalana Amin Exp $
 */
public class PeriodHelper {

    public static String getIndonesianWording(YearMonth period) {
        return String.format("%s %s", getIndonesianMonth(period.getMonthValue()), period.getYear());
    }

    private static String getIndonesianMonth(int month) {
        switch (month) {
            case 1:  return "Januari";
            case 2:  return "Februari";
            case 3:  return "Maret";
            case 4:  return "April";
            case 5:  return "Mei";
            case 6:  return "Juni";
            case 7:  return "Juli";
            case 8:  return "Agustus";
            case 9:  return "September";
            case 10: return "Oktober";
            case 11: return "November";
            case 12: return "Desember";
            default: return "UNKNOWN";
        }
    }

}