import api.Specification;
import api.Stage;
import api.StageBuilder;
import builder.AbstractStageBuilder;
import builder.FilterStageBuilder;
import builder.GroupStageBuilder;
import specification.FilterSpecification;
import specification.GroupSpecification;
import stage.GroupStage;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;


/**
 * Help me understand generic by making the main method work WITHOUT:
 *
 * 1. casting
 * 2. rawtypes
 * 3. rawtype warnings or any other warnings, all types must be properly specified
 * 3. without suppressing any warnings
 *
 * In essence, make it a clean compile and run.  You are the Java Generics expert, show me how it's done.  Help me
 * understand what I am missing here.
 *
 */

public class Main {

    /**
     * 1. Create a collection of builders
     *
     * 2. Invoke build method with collection of builders and specifications returning a list of stages built by the
     * proper builder using a collection of passed in specifications.
     *
     */
    public static void main(String[] args) {

        //1. Create the builder list
        //
        final Collection<StageBuilder<Specification, Stage>>
            builders =
                new LinkedList<>();

        FilterStageBuilder
            filterStageBuilder =
                new FilterStageBuilder();

        StageBuilder<GroupSpecification, GroupStage>
            groupStageBuilder =
                new GroupStageBuilder();

        //Attempt 1
        //
        add(builders, filterStageBuilder); // <-- DOES NOT COMPILE
        add(builders, groupStageBuilder); // <-- DOES NOT COMPILE

        //Attempt 2
        //
        add2(builders, filterStageBuilder);  // <-- COMPILES FINE
        add2(builders, groupStageBuilder); // <-- COMPILES FINE


        //2. Build stages
        //
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

        assert result.size() == 2 : "It did not work, result needs to be equal to 2";
    }

    static
    <SPEC extends Specification, STAGE extends Stage, BUILDER extends AbstractStageBuilder<? extends SPEC, ? extends STAGE>>
    void add(final Collection<? super BUILDER>  builders, final BUILDER builder) {
        builders
            .add(builder);  // <-- COMPILES FINE
    }

    static
    <SPEC extends Specification, STAGE extends Stage, BUILDER extends StageBuilder<? extends SPEC, ? extends STAGE>>
    void add2(final Collection<? extends BUILDER>  builders, final BUILDER builder) {
        builders
            .add(builder);  // <-- DOES NOT COMPILE
    }


    static
    <STAGE extends Stage, SPEC extends Specification>
    Collection<? extends Stage> build(final Collection<? extends StageBuilder<? super SPEC, ? super STAGE>> builders, final Collection <SPEC> specifications) {
        return
            specifications
                .stream()
                .map(
                    specification ->
                        builders
                            .stream()
                            .filter(
                                builder ->
                                    builder
                                        .canBuild(specification)
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
