package cloud.deuterium.testing.anime.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 */

@AnalyzeClasses(packages = "cloud.deuterium.testing.anime",
        importOptions = {ImportOption.DoNotIncludeTests.class})
public class DtoRulesTest {

    @ArchTest
    static final ArchRule DTOs_must_reside_in_a_dto_package =
            classes().that().haveNameMatching(".*Dto").should().resideInAPackage("..dto..")
                    .as("DTOs should reside in a package '..dto..'");

    @ArchTest
    static final ArchRule DTOs_must_have_all_public_fields =
            fields().that().areDeclaredInClassesThat()
                    .resideInAPackage("cloud.deuterium.testing.anime.dto..")
                    .should().notBePrivate()
                    .as("DTOs must not have the private fields");

/*
    @ArchTest
    static final ArchRule entities_must_reside_in_a_domain_package =
            classes().that().areAnnotatedWith(Entity.class).should().resideInAPackage("..domain..")
                    .as("Entities should reside in a package '..domain..'");

    @ArchTest
    static final ArchRule only_DAOs_may_use_the_EntityManager =
            noClasses().that().resideOutsideOfPackage("..dao..")
                    .should().accessClassesThat().areAssignableTo(EntityManager.class)
                    .as("Only DAOs may use the " + EntityManager.class.getSimpleName());

    @ArchTest
    static final ArchRule DAOs_must_not_throw_SQLException =
            noMethods().that().areDeclaredInClassesThat().haveNameMatching(".*Dao")
                    .should().declareThrowableOfType(SQLException.class);
*/
}
