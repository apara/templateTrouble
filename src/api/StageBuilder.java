package api;

public interface StageBuilder<SPEC extends Specification, STAGE extends Stage> {
    STAGE build(SPEC specifications);
    boolean canBuild(SPEC specs);
}
