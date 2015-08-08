package builder;

import specification.GroupSpecification;
import stage.GroupStage;

/**
 * Created by aparansky on 8/6/15.
 */
public class GroupStageBuilder extends AbstractStageBuilder<GroupSpecification, GroupStage> {

    public GroupStageBuilder() {
        super(GroupSpecification.class);
    }

    @Override
    public GroupStage build(final GroupSpecification specifications) {
        return
            new GroupStage();
    }
}
