import api.Specification;
import api.Stage;
import api.StageBuilder;
import builder.FilterStageBuilder;
import builder.GroupStageBuilder;
import specification.FilterSpecification;
import specification.GroupSpecification;
import stage.FilterStage;
import stage.GroupStage;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //Create the builder list
        //
        final Collection<? extends StageBuilder<? extends Specification, ? extends Stage>>
            builders =
                new LinkedList<>();

        StageBuilder<FilterSpecification, FilterStage>
            filterStageBuilder =
                new FilterStageBuilder();

        StageBuilder<GroupSpecification, GroupStage>
            groupStageBuilder =
                new GroupStageBuilder();

        builders.add(filterStageBuilder);
        builders.add(groupStageBuilder);


        final Collection<? extends Stage>
            result =
                build(
                    builders,
                    Arrays.asList(
                        new FilterSpecification(),
                        new GroupSpecification()
                    )
                );

        System.out.println("Created stages: " + result.size());
    }

    static
    <STAGE extends Stage, SPEC extends Specification, BUILDER extends StageBuilder<? extends SPEC, ? extends STAGE>>
    Collection<? extends STAGE> build(final Collection<? super BUILDER> builders, final Collection <? super SPEC> specifications) {
        return
            specifications
                .stream()
                .map(
                    specification ->
                        builders
                            .stream()
                            .filter(
                                builder ->
                                    builder.canBuild(specification)
                            )
                            .findFirst()
                            .orElseThrow(
                                RuntimeException::new
                            )
                            .build(
                                specification
                            )
                )
                .collect(
                    Collectors.toList()
                );
    }
}
