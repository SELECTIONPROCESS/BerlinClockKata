package selectionprocess.berlinclockkata;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BerlinClockActivity extends Activity implements BerlinClockView {

    @BindView(R.id.etDigitalTimeInput)
    EditText etDigitalTimeInput;

    @BindView(R.id.tvBerlinTimeOutput)
    TextView tvBerlinTimeOutput;

    @BindView(R.id.etBerlinTimeInput)
    EditText etBerlinTimeInput;

    @BindView(R.id.tvDigitalTimeOutput)
    TextView tvDigitalTimeOutput;

    private BerlinClockPresenter berlinClockPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berlin_clock);
        ButterKnife.bind(this);

        initializePresenter();
        initalizeViews();
    }

    private void initializePresenter() {
        berlinClockPresenter = new BerlinClockPresenter(this);
    }

    private void initalizeViews() {
        etDigitalTimeInput.addTextChangedListener(digitalTimeTextWather);
        etBerlinTimeInput.addTextChangedListener(berlinTimeTextWather);
    }

    private TextWatcher digitalTimeTextWather = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            berlinClockPresenter.digitalTimeChanged(s.toString());
        }
    };

    private TextWatcher berlinTimeTextWather = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            berlinClockPresenter.berlinTimeChanged(s.toString());
        }
    };

    @Override
    public void updateBerlinTimeOutput(String output) {
        tvBerlinTimeOutput.setText(output);
    }

    @Override
    public void updateDigitalTimeOutput(String output) {
        tvDigitalTimeOutput.setText(output);
    }
}
