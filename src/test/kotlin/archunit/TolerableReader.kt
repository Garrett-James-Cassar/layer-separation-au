package archunit

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.domain.JavaField
import com.tngtech.archunit.core.domain.JavaParameterizedType
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchCondition
import com.tngtech.archunit.lang.ConditionEvents
import com.tngtech.archunit.lang.SimpleConditionEvent
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields
import org.junit.jupiter.api.Test

class TolerableReader {

    @Test
    fun `Consuming DTOs not have enum fields`() {
        val importedClasses: JavaClasses = ClassFileImporter().importPackages(BASE_PACKAGE)

        val shouldNotHaveEnumFields = object : ArchCondition<JavaField>("Consuming DTOs should not have enum fields") {
            override fun check(javaField: JavaField, events: ConditionEvents) {
                if (javaField.rawType.isEnum) {
                    events.add(SimpleConditionEvent.violated(javaField, "Field $javaField is an enum and thus violates the rule"))
                }
                val javaType = javaField.type

                if(javaType is JavaParameterizedType){
                    for (typeArg in javaType.actualTypeArguments) {
                        if (Class.forName(typeArg.name).isEnum) {
                            events.add(SimpleConditionEvent.violated(javaField, "Field $javaField is an enum and thus violates the rule"))
                        }
                    }
                }

                if(javaType.javaClass.typeParameters.javaClass.isEnum){
                    events.add(SimpleConditionEvent.violated(javaField, "Field $javaField is an enum and thus violates the rule"))
                }

            }
        }

        val enumFieldRule = fields()
            .that().areDeclaredInClassesThat().resideInAnyPackage("..dto..")
            .should(shouldNotHaveEnumFields)

        enumFieldRule.check(importedClasses)
    }
}
