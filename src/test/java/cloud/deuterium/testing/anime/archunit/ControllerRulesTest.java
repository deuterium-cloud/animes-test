package cloud.deuterium.testing.anime.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 */

@AnalyzeClasses(packages = "cloud.deuterium.testing.anime.controller",
        importOptions = {ImportOption.DoNotIncludeTests.class})
public class ControllerRulesTest {

    @ArchTest
    static final ArchRule layer_dependencies_are_respected =
            methods().that().arePublic()
                    .and().areDeclaredInClassesThat()
                    .resideInAPackage("cloud.deuterium.testing.anime.controller..")
                    .and().areDeclaredInClassesThat()
                    .haveSimpleNameEndingWith("Controller")
                    .and().areDeclaredInClassesThat()
                    .areAnnotatedWith(RestController.class)
                    .should().beAnnotatedWith(RequestMapping.class)
                    .orShould().beAnnotatedWith(GetMapping.class)
                    .orShould().beAnnotatedWith(PostMapping.class)
                    .orShould().beAnnotatedWith(PutMapping.class)
                    .orShould().beAnnotatedWith(DeleteMapping.class);

    @ArchTest
    static ArchRule all_public_methods_in_the_controller_layer_should_return_API_response_wrappers =
            methods()
                    .that().areDeclaredInClassesThat()
                    .resideInAPackage("cloud.deuterium.testing.anime.controller..")
                    .and().arePublic()
                    .should().haveRawReturnType(ResponseEntity.class)
                    .because("Return type must be 'ResponseEntity'");

}
