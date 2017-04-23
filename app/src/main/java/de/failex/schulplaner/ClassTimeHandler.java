package de.failex.schulplaner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by felix on 23.04.17.
 */

public class ClassTimeHandler {

    public static int getCurrentClass() {
        try {
            String currTime = new SimpleDateFormat("HH:mm").format(new Date());
            String ersteStd = "08:00";
            String ersteStdVrb = "08:45";
            String zweiteStd = "08:50";
            String zweiteStdVrb = "09:35";
            String dritteStd = "09:40";
            String dritteStdVrb = "10:25";
            String vierteStd = "10:55";
            String vierteStdVrb = "11:40";
            String fuenfteStd = "11:45";
            String fuenfteStdVrb = "12:30";
            String sechsteStd = "12:35";
            String sechsteStdVrb = "13:20";
            String siebteStd = "13:40";
            String siebteStdVrb = "14:25";
            String achteStd = "14:30";
            String achteStdVrb = "15:15";
            String neunteStd = "15:15";
            String neunteStdVrb = "16:00";

            Date currTimed = new SimpleDateFormat("HH:mm").parse(currTime);
            Date time1 = new SimpleDateFormat("HH:mm").parse(ersteStd);
            Date time2 = new SimpleDateFormat("HH:mm").parse(ersteStdVrb);
            Date timezweite = new SimpleDateFormat("HH:mm").parse(zweiteStd);
            Date timezweitevrb = new SimpleDateFormat("HH:mm").parse(zweiteStdVrb);
            Date timedritte = new SimpleDateFormat("HH:mm").parse(dritteStd);
            Date timedrittevrb = new SimpleDateFormat("HH:mm").parse(dritteStdVrb);
            Date timevierte = new SimpleDateFormat("HH:mm").parse(vierteStd);
            Date timeviertevrb = new SimpleDateFormat("HH:mm").parse(vierteStdVrb);
            Date timefuenfte = new SimpleDateFormat("HH:mm").parse(fuenfteStd);
            Date timefuenftevrb = new SimpleDateFormat("HH:mm").parse(fuenfteStdVrb);
            Date timesechste = new SimpleDateFormat("HH:mm").parse(sechsteStd);
            Date timesechstevrb = new SimpleDateFormat("HH:mm").parse(sechsteStdVrb);
            Date timesiebte = new SimpleDateFormat("HH:mm").parse(siebteStd);
            Date timesiebtevrb = new SimpleDateFormat("HH:mm").parse(siebteStdVrb);
            Date timeachte = new SimpleDateFormat("HH:mm").parse(achteStd);
            Date timeachtevrb = new SimpleDateFormat("HH:mm").parse(achteStdVrb);
            Date timeneunte = new SimpleDateFormat("HH:mm").parse(neunteStd);
            Date timeneuntevrb = new SimpleDateFormat("HH:mm").parse(neunteStdVrb);



            java.util.Calendar cal3 = java.util.Calendar.getInstance();
            java.util.Calendar cal = java.util.Calendar.getInstance();
            java.util.Calendar cal2 = java.util.Calendar.getInstance();
            java.util.Calendar calzweite = java.util.Calendar.getInstance();
            java.util.Calendar caldritte = java.util.Calendar.getInstance();
            java.util.Calendar calvierte = java.util.Calendar.getInstance();
            java.util.Calendar calfuenfte = java.util.Calendar.getInstance();
            java.util.Calendar calsechste = java.util.Calendar.getInstance();
            java.util.Calendar calsiebte = java.util.Calendar.getInstance();
            java.util.Calendar calachte = java.util.Calendar.getInstance();
            java.util.Calendar calneunte = java.util.Calendar.getInstance();
            java.util.Calendar calzweitevrb = java.util.Calendar.getInstance();
            java.util.Calendar caldrittevrb = java.util.Calendar.getInstance();
            java.util.Calendar calviertevrb = java.util.Calendar.getInstance();
            java.util.Calendar calfuenftevrb = java.util.Calendar.getInstance();
            java.util.Calendar calsechstevrb = java.util.Calendar.getInstance();
            java.util.Calendar calsiebtevrb = java.util.Calendar.getInstance();
            java.util.Calendar calachtevrb = java.util.Calendar.getInstance();
            java.util.Calendar calneuntevrb = java.util.Calendar.getInstance();



            cal3.setTime(currTimed);
            cal.setTime(time1);
            cal2.setTime(time2);
            calzweite.setTime(timezweite);
            caldritte.setTime(timedritte);
            calvierte.setTime(timevierte);
            calfuenfte.setTime(timefuenfte);
            calsechste.setTime(timesechste);
            calsiebte.setTime(timesiebte);
            calachte.setTime(timeachte);
            calneunte.setTime(timeneunte);
            calzweitevrb.setTime(timezweitevrb);
            caldrittevrb.setTime(timedrittevrb);
            calviertevrb.setTime(timeviertevrb);
            calfuenftevrb.setTime(timefuenftevrb);
            calsechstevrb.setTime(timesechstevrb);
            calsiebtevrb.setTime(timesiebtevrb);
            calachtevrb.setTime(timeachtevrb);
            calneuntevrb.setTime(timeneuntevrb);



            cal2.add(java.util.Calendar.DATE, 1);
            cal3.add(java.util.Calendar.DATE, 1);
            calzweite.add(java.util.Calendar.DATE, 1);
            caldritte.add(java.util.Calendar.DATE, 1);
            calvierte.add(java.util.Calendar.DATE, 1);
            calfuenfte.add(java.util.Calendar.DATE, 1);
            calsechste.add(java.util.Calendar.DATE, 1);
            calsiebte.add(java.util.Calendar.DATE, 1);
            calachte.add(java.util.Calendar.DATE, 1);
            calneunte.add(java.util.Calendar.DATE, 1);
            calzweitevrb.add(java.util.Calendar.DATE, 1);
            caldrittevrb.add(java.util.Calendar.DATE, 1);
            calviertevrb.add(java.util.Calendar.DATE, 1);
            calfuenftevrb.add(java.util.Calendar.DATE, 1);
            calsechstevrb.add(java.util.Calendar.DATE, 1);
            calsiebtevrb.add(java.util.Calendar.DATE, 1);
            calachtevrb.add(java.util.Calendar.DATE, 1);
            calneuntevrb.add(java.util.Calendar.DATE, 1);


            Date neu = cal3.getTime();

            if (neu.after(cal.getTime()) && neu.before(cal2.getTime())) {
                return 1;
            } else if (neu.after(calzweite.getTime()) && neu.before(calzweitevrb.getTime())) {
                return 2;
            } else if (neu.after(caldritte.getTime()) && neu.before(caldrittevrb.getTime())) {
                return 3;
            } else if (neu.after(calvierte.getTime()) && neu.before(calviertevrb.getTime())) {
                return 4;
            } else if (neu.after(calfuenfte.getTime()) && neu.before(calfuenftevrb.getTime())) {
                return 5;
            } else if (neu.after(calsechste.getTime()) && neu.before(calsechstevrb.getTime())) {
                return 6;
            } else if (neu.after(calsiebte.getTime()) && neu.before(calsiebtevrb.getTime())) {
                return 7;
            } else if (neu.after(calachte.getTime()) && neu.before(calachtevrb.getTime())) {
                return 8;
            } else if (neu.after(calneunte.getTime()) && neu.before(calneuntevrb.getTime())) {
                return 9;
            }

        }
        catch(ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
