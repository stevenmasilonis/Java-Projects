import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Steven Masilonis
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {
        final NaturalNumber output = model.top();
        final NaturalNumber input = model.bottom();
        view.updateTopDisplay(output);
        view.updateBottomDisplay(input);
        view.updateSubtractAllowed(output.compareTo(input) >= 0);
        view.updateDivideAllowed(!input.isZero());
        view.updatePowerAllowed(
                input.compareTo(NNCalcController1.INT_LIMIT) <= 0);
        view.updateRootAllowed(input.compareTo(NNCalcController1.TWO) >= 0
                && input.compareTo(NNCalcController1.INT_LIMIT) <= 0);

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {
        this.model.top().copyFrom(this.model.bottom());
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {

        NaturalNumber answer = this.model.top().newInstance();
        answer.transferFrom(this.model.top());
        answer.add(this.model.bottom());
        this.model.bottom().transferFrom(answer);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {

        NaturalNumber answer = this.model.top().newInstance();
        answer.transferFrom(this.model.top());
        answer.subtract(this.model.bottom());
        this.model.bottom().transferFrom(answer);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {

        NaturalNumber answer = this.model.top().newInstance();
        answer.transferFrom(this.model.top());
        answer.multiply(this.model.bottom());
        this.model.bottom().transferFrom(answer);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processDivideEvent() {

        NaturalNumber answer = this.model.top().newInstance();
        answer.transferFrom(this.model.top());
        answer.divide(this.model.bottom());
        this.model.bottom().transferFrom(answer);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {

        NaturalNumber answer = this.model.top().newInstance();
        answer.transferFrom(this.model.top());
        answer.power(this.model.bottom().toInt());
        this.model.bottom().transferFrom(answer);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {

        NaturalNumber answer = this.model.top().newInstance();
        answer.transferFrom(this.model.top());
        answer.root(this.model.bottom().toInt());
        this.model.bottom().transferFrom(answer);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        this.model.bottom().multiplyBy10(digit);
        updateViewToMatchModel(this.model, this.view);

    }

}
