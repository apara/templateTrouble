package api;

import java.util.Optional;
import java.util.function.Supplier;

public interface StageBuilder<STAGE extends Stage> {
    Optional<Supplier<STAGE>> supplier(Specification specification);
}
