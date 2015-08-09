package builder;

import api.Specification;
import api.Stage;
import api.StageBuilder;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class AbstractStageBuilder<SPEC extends Specification, STAGE extends Stage> implements StageBuilder<STAGE> {
    private Class<? extends SPEC>
        specClass;

    public AbstractStageBuilder(final Class<? extends SPEC> specClass) {
        this.specClass = specClass;
    }

    @Override
    public Optional<Supplier<STAGE>> supplier(final Specification specification) {

        return
            specClass.isAssignableFrom(specification.getClass())
                ? Optional.of(() -> build(specClass.cast(specification)))
                : Optional.empty();
    }

    protected abstract STAGE build (SPEC specification);
}
