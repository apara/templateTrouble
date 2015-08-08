package builder;

import specification.FilterSpecification;
import stage.FilterStage;

public class FilterStageBuilder extends AbstractStageBuilder<FilterSpecification, FilterStage> {

    public FilterStageBuilder() {
        super(FilterSpecification.class);
    }

    @Override
    public FilterStage build(final FilterSpecification specifications) {
        return
            new FilterStage(specifications);
    }
}
