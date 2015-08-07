package api;

/**
 * Created by aparansky on 8/6/15.
 */
public interface StageBuilder<SPEC extends Specifications, STAGE extends Stage> {
    STAGE build(SPEC specifications);
    boolean canBuild(SPEC specs);
}
