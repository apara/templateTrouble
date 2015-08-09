# templateTrouble 

I was creating a builder pattern of builders which can build stages for specifications.  The main problem was that
my builder was defined in terms of BOTH stage and specification.  This made it difficult to work with builders as the 
compiler was trying to enforce strong type checking.

Here is the original definition:

```Java
public interface StageBuilder<SPEC extends Specification, STAGE extends Stage> {
    STAGE build(SPEC specifications);
    boolean canBuild(SPEC specs);
}
```

Here is the updated definition, which actually separates the "creation" step of the Stage and allows it to be executed 
at a different time by returning a Supplier:

```Java
public interface StageBuilder<STAGE extends Stage> {
    Optional<Supplier<STAGE>> supplier(Specification specification);
}
```
In fact, as the Stackoverlow.com comment from Tim states, my code was attempting to use StageBuilder as the second
definition instead of as what I had defined it at.  As a result, compiler was fighting me with regard of usage of 
stages and specifications.

Here is a a bit simpler version:

```Java
public interface StageBuilder<STAGE extends Stage> {
    Optional<STAGE> build(Specification specification);
}
```

Here, the STAGE is built right when the build method is invoked.  The repository was updated to reflect the first 
version of the solution.

See complete discussion of this the issue behind this repository here: 
http://stackoverflow.com/questions/31887042/not-sure-how-to-fix-this-issue-with-java-generics