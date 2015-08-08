package builder;

import specification.GroupSpecification;
import stage.GroupStage;

public class GroupStageBuilder extends AbstractStageBuilder<GroupSpecification, GroupStage> {

    public GroupStageBuilder() {
        super(GroupSpecification.class);
    }

    @Override
    public GroupStage build(final GroupSpecification specifications) {
        return
            new GroupStage(specifications);
    }
}
