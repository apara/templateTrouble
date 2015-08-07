import api.Specifications;
import api.Stage;
import api.StageBuilder;
import builder.FilterStageBuilder;
import builder.GroupStageBuilder;
import specification.BasicFilterSpecifications;
import specification.BasicGroupSpecifications;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //Create the builder list
        //
        final Collection<? extends StageBuilder<? extends Specifications,? extends Stage>>
            builders =
                new LinkedList<>();

        builders.add(new FilterStageBuilder());
        builders.add(new GroupStageBuilder());


        final Collection<Stage>
            result =
                build(
                    builders,
                    Arrays.asList(
                        new BasicFilterSpecifications(),
                        new BasicGroupSpecifications()
                    )
                );

        System.out.println("Hello World!");
    }


    static Collection<Stage> build(final Collection<StageBuilder<Specifications,?>> builders,  final Collection <Specifications> specs) {
        return
            specs
                .stream()
                .map(
                    specifications ->
                        builders
                            .stream()
                            .filter(
                                builder ->
                                    builder
                                        .canBuild(specifications)
                            )
                            .findFirst()
                            .orElseThrow(
                                () ->
                                    new RuntimeException(
                                        "Builder for not found for " + specifications
                                    )
                            )
                            .build(
                                specifications
                            )
                )
                .collect(
                    Collectors.toList()
                );
    }
}
