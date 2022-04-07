package cloud.deuterium.testing.anime.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 */

@AnalyzeClasses(packages = "cloud.deuterium.testing.anime.service",
        importOptions = {ImportOption.DoNotIncludeTests.class})
public class ServiceRulesTest {

    @ArchTest
    static ArchRule classes_named_service_should_be_in_a_service_package =
            classes()
                    .that().haveSimpleNameContaining("Service")
                    .should().resideInAPackage("..service..");

    @ArchTest
    static ArchRule services_should_ending_with_service =
            classes()
                    .that().resideInAPackage("..service..")
                    .and().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameContaining("Service")
                    .andShould().haveSimpleNameEndingWith("Impl");
}
