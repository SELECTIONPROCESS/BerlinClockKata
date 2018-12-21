package selectionprocess.berlinclockkata.converter;

import java.util.Locale;

public class DigitalTimeConverter implements TimeConverter {

    private static final char LAMP_YELLOW = 'Y';
    private static final char LAMP_RED = 'R';
    private static final char LAMP_OFF = 'O';

    private static final int FIVE_HOURS_FROM = 1;
    private static final int FIVE_HOURS_TO = 5;
    private static final int SINGLE_HOURS_FROM = 5;
    private static final int SINGLE_HOURS_TO = 9;
    private static final int FIVE_MINUTES_FROM = 9;
    private static final int FIVE_MINUTES_TO = 20;
    private static final int SINGLE_MINUTES_FROM = 20;
    private static final int SINGLE_MINUTES_TO = 24;

    private static final int MULTIPLIER = 5;

    /**
     * Returns digital time equivalent of input berlin time.
     * If argument berlin time is not valid, returns empty string.
     *
     * @param berlinTime time in berlin format
     */
    @Override
    public String convert(String berlinTime) {

        if (isValidBerlinTime(berlinTime)) {
            return getDigitalTimeFrom(berlinTime);
        }
        return "";
    }

    private String getDigitalTimeFrom(String berlinTime) {
        String fiveHoursRow = berlinTime.substring(FIVE_HOURS_FROM, FIVE_HOURS_TO);
        String singleHoursRow = berlinTime.substring(SINGLE_HOURS_FROM, SINGLE_HOURS_TO);
        String fiveMinutesRow = berlinTime.substring(FIVE_MINUTES_FROM, FIVE_MINUTES_TO);
        String singleMinutesRow = berlinTime.substring(SINGLE_MINUTES_FROM, SINGLE_MINUTES_TO);

        int fiveHoursMultiplier = getFiveHoursMultiplier(fiveHoursRow);
        int singleHours = getSingleHours(singleHoursRow);
        int fiveMinutesMultiplier = getFiveMinutesMultiplier(fiveMinutesRow);
        int singleMinutes = getSingleMinutes(singleMinutesRow);

        int seconds = getSeconds(berlinTime.charAt(0));
        int hours = fiveHoursMultiplier * MULTIPLIER + singleHours;
        int minutes = fiveMinutesMultiplier * MULTIPLIER + singleMinutes;

        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
    }

    private int getSeconds(char seconds) {
        return seconds == LAMP_YELLOW ? 0 : 1;
    }

    private int getSingleMinutes(String singleMinutesRow) {
        return characterCountIn(singleMinutesRow, LAMP_YELLOW);
    }

    private int getFiveMinutesMultiplier(String fiveMinutesRow) {
        return 11 - characterCountIn(fiveMinutesRow, LAMP_OFF);
    }

    private int getSingleHours(String singleHoursRow) {
        return characterCountIn(singleHoursRow, LAMP_RED);
    }

    private int getFiveHoursMultiplier(String fiveHoursRow) {
        return characterCountIn(fiveHoursRow, LAMP_RED);
    }

    private int characterCountIn(String text, char character) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == character) {
                count++;
            }
        }
        return count;
    }

    private boolean isValidBerlinTime(String berlinTime) {
        return berlinTime.length() == 24;
    }
}
