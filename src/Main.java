import api.Specifications;
import api.Stage;
import api.StageBuilder;
import builder.FilterStageBuilder;
import builder.GroupStageBuilder;
import specification.FilterSpecifications;
import specification.GroupSpecifications;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //Create the builder list
        //
        final Collection<StageBuilder<Specifications,?>>
            builders =
                new LinkedList<StageBuilder<Specifications,?>>();

        //*** THESE TWO LINES DO NOT COMPILE, cannot add specific builders to collection ***
        //
        builders.add(new FilterStageBuilder());
        builders.add(new GroupStageBuilder());

        final Collection<Stage>
            result =
                build(
                    builders,
                    Arrays.asList(
                        new FilterSpecifications(),
                        new GroupSpecifications()
                    )
                );

        System.out.println("Created stages: " + result.size());
    }


    static Collection<Stage> build(final Collection<StageBuilder<Specifications,?>> builders,  final Collection <Specifications> specifications) {
        return
            specifications
                .stream()
                .map(
                    spec ->
                        builders
                            .stream()
                            .filter(
                                builder ->
                                    builder
                                        .canBuild(spec)
                            )
                            .findFirst()
                            .orElseThrow(
                                () ->
                                    new RuntimeException(
                                        "Builder not found for " + spec
                                    )
                            )
                            .build(
                                spec
                            )
                )
                .collect(
                    Collectors.toList()
                );
    }
}
