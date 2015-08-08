package builder;

import specification.FilterSpecification;
import stage.FilterStage;

/**
 * Created by aparansky on 8/6/15.
 */
public class FilterStageBuilder extends AbstractStageBuilder<FilterSpecification, FilterStage> {

    public FilterStageBuilder() {
        super(FilterSpecification.class);
    }

    @Override
    public FilterStage build(final FilterSpecification specifications) {
        return
            new FilterStage();
    }
}
