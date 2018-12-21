package selectionprocess.berlinclockkata.converter;

public class BerlinTimeConverter implements TimeConverter {

    @Override
    public String convert(String digitalTime) {
        return "";
    }

    boolean isValidDigitalTime(String digitalTime) {
        return digitalTime.matches("(2[0-3]|[0-1][0-9]):[0-5][0-9]:[0-5][0-9]");
    }
}
