package selectionprocess.berlinclockkata;

import selectionprocess.berlinclockkata.converter.BerlinTimeConverter;
import selectionprocess.berlinclockkata.converter.DigitalTimeConverter;

public class BerlinClockPresenter {

    private BerlinClockView berlinClockView;
    private BerlinTimeConverter berlinTimeConverter;
    private DigitalTimeConverter digitalTimeConverter;

    BerlinClockPresenter(BerlinClockView berlinClockView) {
        this.berlinClockView = berlinClockView;
        berlinTimeConverter = new BerlinTimeConverter();
        digitalTimeConverter = new DigitalTimeConverter();
    }

    void digitalTimeChanged(String digitalTime) {

        berlinClockView.updateBerlinTimeOutput(berlinTimeConverter.convert(digitalTime));
    }

    void berlinTimeChanged(String berlinTime) {

        berlinClockView.updateDigitalTimeOutput(digitalTimeConverter.convert(berlinTime));
    }
}
