package selectionprocess.berlinclockkata.converter;

import static selectionprocess.berlinclockkata.converter.TimeConstants.*;

public class BerlinTimeConverter implements TimeConverter {

    private static final String validDigitalTimeRegex = "(2[0-3]|[0-1][0-9]):[0-5][0-9]:[0-5][0-9]";

    /**
     * Returns berlin time equivalent of input digital time.
     * If argument berlin time is not valid, returns empty string.
     *
     * @param digitalTime time in digital format (HH:mm:ss)
     * @return berlin time equivalent of input digital time.
     */
    @Override
    public String convert(String digitalTime) {

        if (isValidDigitalTime(digitalTime)) {
            return getBerlinTimeFrom(digitalTime);
        }
        return "";
    }

    private String getBerlinTimeFrom(String digitalTime) {
        int hour = getIntFromStringInRange(digitalTime, HOUR_FROM, HOUR_TO);
        int minute = getIntFromStringInRange(digitalTime, MINUTE_FROM, MINUTE_TO);
        int second = getIntFromStringInRange(digitalTime, SECOND_FROM, SECOND_TO);

        StringBuilder sb = new StringBuilder(24);
        sb.append(getSecondsLamp(second));
        sb.append(getFiveHoursRow(hour));
        sb.append(getSingleHoursRow(hour));
        sb.append(getFiveMinutesRow(minute));
        sb.append(getSingleMinutesRow(minute));
        return sb.toString();
    }

    private char getSecondsLamp(int seconds) {
        return (seconds % 2 == 0) ? LAMP_YELLOW : LAMP_OFF;
    }

    private String getSingleMinutesRow(int minute) {
        int remainder = getRemainder(minute, MULTIPLIER);
        return getFormattedString(4, remainder, LAMP_YELLOW, LAMP_OFF);
    }

    /**
     * Returns five minutes row of Berlin time.
     * String is filled with 'Y' from start to off lamp, but
     * each third character is filled with 'R'.
     *
     * @param minute converts this into five minutes row
     */
    private String getFiveMinutesRow(int minute) {
        int quotient = getQuotient(minute, MULTIPLIER);
        StringBuilder sb = new StringBuilder(11);
        int i = 0;
        while (i < 11) {
            if (i < quotient) {
                if ((i + 1) % 3 == 0) {
                    sb.append(LAMP_RED);
                } else {
                    sb.append(LAMP_YELLOW);
                }
            } else {
                sb.append(LAMP_OFF);
            }
            i++;
        }
        return sb.toString();
    }

    private String getFiveHoursRow(int hour) {
        int quotient = getQuotient(hour, MULTIPLIER);
        return getFormattedString(4, quotient, LAMP_RED, LAMP_OFF);
    }

    private String getSingleHoursRow(int hour) {
        int remainder = getRemainder(hour, MULTIPLIER);
        return getFormattedString(4, remainder, LAMP_RED, LAMP_OFF);
    }

    /**
     * Returns a string with desired length.
     * First fillLength characters of string is filled with fillChar,
     * Reminder characters are filled with reminderChar.
     *
     * @param totalLength length of desired string
     * @param fillLength length of desired fillChar
     * @param fillChar fills first fillLength characters with this
     * @param reminderChar fills remaning characters with this
     */
    private String getFormattedString(int totalLength, int fillLength, char fillChar, char reminderChar) {
        StringBuilder sb = new StringBuilder(totalLength);
        int i = 0;
        while (i < totalLength) {
            if (i < fillLength) {
                sb.append(fillChar);
            } else {
                sb.append(reminderChar);
            }
            i++;
        }
        return sb.toString();
    }

    int getIntFromStringInRange(String text, int from, int to) {
        String substring = text.substring(from, to);
        return Integer.valueOf(substring);
    }

    int getQuotient(int dividend, int divisor) {
        return dividend/divisor;
    }

    int getRemainder(int dividend, int divisor) {
        return dividend%divisor;
    }

    boolean isValidDigitalTime(String digitalTime) {
        return digitalTime.matches(validDigitalTimeRegex);
    }
}
