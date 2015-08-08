package stage;

import api.Specification;
import api.Stage;

public class AbstractFilterStage<SPEC extends Specification> implements Stage {
    private SPEC
        specification;

    public AbstractFilterStage(final SPEC specification) {
        this.specification = specification;
    }
}
