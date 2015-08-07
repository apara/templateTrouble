package builder;

import api.Specifications;
import api.Stage;
import api.StageBuilder;

/**
 * Created by aparansky on 8/6/15.
 */
public abstract class AbstractStageBuilder<SPEC extends Specifications, STAGE extends Stage> implements StageBuilder<SPEC, STAGE> {
    private Class<? extends SPEC>
        specClass;

    public AbstractStageBuilder(final Class<? extends SPEC> specClass) {
        this.specClass = specClass;
    }

    @Override
    public boolean canBuild(final SPEC specs) {
        return
            specClass.isAssignableFrom(specs.getClass());
    }
}
