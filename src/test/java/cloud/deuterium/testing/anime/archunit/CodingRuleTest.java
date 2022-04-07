package cloud.deuterium.testing.anime.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.CompositeArchRule;
import org.slf4j.Logger;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 * https://www.archunit.org/userguide/html/000_Index.html
 */

@AnalyzeClasses(packages = "cloud.deuterium.testing.anime",
        importOptions = {ImportOption.DoNotIncludeTests.class})
public class CodingRuleTest {

    @ArchTest
    private final ArchRule no_generic_exceptions =
            NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS; // Should not throw Throwable, Exception, RuntimeException

    @ArchTest
    private final ArchRule no_java_util_logging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    @ArchTest
    private final ArchRule loggers_should_be_private_static_final =
            fields().that().haveRawType(Logger.class)
                    .should().bePrivate()
                    .andShould().beStatic()
                    .andShould().beFinal()
                    .because("we agreed on this convention");

    @ArchTest
    static final ArchRule no_classes_should_access_standard_streams_or_use =
            CompositeArchRule.of(NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS) // Should not use System.out
                    .and(NO_CLASSES_SHOULD_USE_FIELD_INJECTION); // Should not use @Autowired
}
