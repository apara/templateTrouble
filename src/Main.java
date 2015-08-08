import api.Specification;
import api.Stage;
import api.StageBuilder;
import builder.FilterStageBuilder;
import builder.GroupStageBuilder;
import specification.FilterSpecifications;
import specification.GroupSpecifications;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        //Create the builder list
        //
        final Collection<StageBuilder<? extends Specification,? extends Stage>>
            builders =
                new LinkedList<>();

        builders.add(new FilterStageBuilder());
        builders.add(new GroupStageBuilder());

        final Collection<Stage>
            result =
                build(
                    (Collection<StageBuilder<Specification,Stage>>) (Object) builders,
                    Arrays.asList(
                        new FilterSpecifications(),
                        new GroupSpecifications()
                    )
                );

        System.out.println("Created stages: " + result.size());
    }



    static
    //<SPEC extends Specification, STAGE extends Stage>
    //Collection<Stage> build(final Collection<StageBuilder<? extends SPEC,? extends STAGE>> builders,  final Collection <? extends Specification> specifications) {
    Collection<Stage> build(final Collection<StageBuilder<Specification,Stage>> builders,  final Collection <Specification> specifications) {
        final Collection<Stage>
            result =
                new LinkedList<>();

        for (final Specification spec : specifications) {
            final StageBuilder<Specification, Stage>
                builder =

                    builders
                        .stream()
                        .filter(
                            b ->
                                canBuild(
                                    b,
                                    spec
                                )
                        )
                        .findFirst()
                        .orElseThrow(
                            () ->
                                new RuntimeException(
                                    "Builder not found for " + spec
                                )
                        );


            result
                .add(
                    build(
                        builder,
                        spec
                    )
                );
        }

        return
            result;
    }

    static
    boolean canBuild(final StageBuilder<Specification, Stage> builder, final Specification spec) {
        return
            builder.canBuild(spec);
    }

    static
    <SPEC extends Specification, STAGE extends Stage>
    STAGE build(final StageBuilder<SPEC, STAGE> builder, final SPEC spec) {
        return
            builder.build(spec);
    }
}
