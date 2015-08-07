package builder;

import specification.GroupSpecifications;
import stage.GroupStage;

/**
 * Created by aparansky on 8/6/15.
 */
public class GroupStageBuilder extends AbstractStageBuilder<GroupSpecifications, GroupStage> {

    public GroupStageBuilder() {
        super(GroupSpecifications.class);
    }

    @Override
    public GroupStage build(final GroupSpecifications specifications) {
        return
            new GroupStage();
    }
}
