import api.Specification;
import api.Stage;
import api.StageBuilder;
import builder.FilterStageBuilder;
import builder.GroupStageBuilder;
import specification.FilterSpecification;
import specification.GroupSpecification;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * See complete discussion of the issue here:
 * http://stackoverflow.com/questions/31887042/not-sure-how-to-fix-this-issue-with-java-generics
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
        final Collection<StageBuilder<? extends Stage>>
            builders =
                new LinkedList<>();

        builders.add(new FilterStageBuilder());
        builders.add(new GroupStageBuilder());


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
    Collection<? extends Stage> build(final Collection<StageBuilder<? extends Stage>> builders, final Collection <? extends Specification> specifications) {
        return
            specifications
                .stream()
                .map(
                    specification ->
                        builders
                            .stream()
                            .map(
                                builder ->
                                    builder.supplier(specification)
                            )
                            .filter(
                                Optional::isPresent
                            )
                            .findFirst()
                            .orElseThrow(
                                RuntimeException::new
                            )
                            .get() //get to return supplier
                            .get() //to execute supplier and retrieve the value
                )
                .collect(
                    Collectors.toList()
                );
    }
}
