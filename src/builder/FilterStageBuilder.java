package builder;

import specification.FilterSpecifications;
import stage.FilterStage;

/**
 * Created by aparansky on 8/6/15.
 */
public class FilterStageBuilder extends AbstractStageBuilder<FilterSpecifications, FilterStage> {

    public FilterStageBuilder() {
        super(FilterSpecifications.class);
    }

    @Override
    public FilterStage build(final FilterSpecifications specifications) {
        return
            new FilterStage();
    }
}
