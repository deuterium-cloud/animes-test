package cloud.deuterium.testing.anime.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.scheduling.annotation.Async;

import static com.tngtech.archunit.library.ProxyRules.no_classes_should_directly_call_other_methods_declared_in_the_same_class_that_are_annotated_with;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 */

@AnalyzeClasses(packages = "cloud.deuterium.testing.anime",
        importOptions = {ImportOption.DoNotIncludeTests.class})
public class ProxyRulesTest {

    @ArchTest
    static ArchRule no_bypass_of_proxy_logic =
            no_classes_should_directly_call_other_methods_declared_in_the_same_class_that_are_annotated_with(Async.class);

}
