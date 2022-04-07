package cloud.deuterium.testing.anime.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 * https://www.archunit.org/userguide/html/000_Index.html
 */

@AnalyzeClasses(packages = {
        "cloud.deuterium.testing.anime.controller",
        "cloud.deuterium.testing.anime.service",
        "cloud.deuterium.testing.anime.repository"},
        importOptions = {ImportOption.DoNotIncludeTests.class})
public class LayeredArchitectureTest {

    @ArchTest
    static final ArchRule layer_dependencies_are_respected = layeredArchitecture()

            .layer("Controllers").definedBy("cloud.deuterium.testing.anime.controller..")
            .layer("Services").definedBy("cloud.deuterium.testing.anime.service..")
            .layer("Persistence").definedBy("cloud.deuterium.testing.anime.repository..")

            .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
            .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Services");
}
