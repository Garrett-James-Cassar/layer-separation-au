package archunit

import org.junit.jupiter.api.Test

import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.domain.properties.HasParameterTypes
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchCondition

import com.tngtech.archunit.lang.ConditionEvents
import com.tngtech.archunit.lang.SimpleConditionEvent
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods


const val BASE_PACKAGE = "cool.project"

class LayerSeparationTests {

    @Test
    fun `Connector classes should only reference adapters and services`() {
        val importedClasses: JavaClasses = ClassFileImporter().importPackages(BASE_PACKAGE)

        val canOnlyAccessAdaptersAndServices = object : ArchCondition<JavaClass>("can only access classes in the adaptor and service packages") {
            override fun check(javaClass: JavaClass, events: ConditionEvents) {
                val disallowedDependencies = javaClass.directDependenciesFromSelf.filter { dependency ->
                    dependency.targetClass.packageName.contains(BASE_PACKAGE) && !(
                        dependency.targetClass.packageName.contains("adaptor")
                        || dependency.targetClass.packageName.contains("service")
                                || dependency.targetClass.packageName.contains("dto")
                                || dependency.targetClass.packageName.contains("stub.database")
                                || dependency.targetClass.packageName.contains("error")

                            )
                }

                if (disallowedDependencies.isNotEmpty()) {
                    events.add(SimpleConditionEvent.violated(javaClass, "$javaClass has disallowed dependencies $disallowedDependencies"))
                }
            }
        }

        val accessAdaptorsAndServicesRule = classes()
            .that().resideInAnyPackage("..connectors..")
            .should(canOnlyAccessAdaptersAndServices)
            .`as`("Connector classes should only reference adapters and services")

        accessAdaptorsAndServicesRule.check(importedClasses)
    }

    @Test
    fun `Connectors should only reference dtos or primitive types in their parameters`() {
        val importedClasses: JavaClasses = ClassFileImporter().importPackages(BASE_PACKAGE)

        val haveDtoOrPrimitiveParameterTypes = object : ArchCondition<HasParameterTypes>("Connectors should only reference dtos or primitive types in their parameters") {
            override fun check(parameterTypes: HasParameterTypes, events: ConditionEvents) {
                val paramTypes = parameterTypes.rawParameterTypes

                val disallowedTypes = paramTypes.filter {
                    (it.packageName.contains(BASE_PACKAGE) && !it.packageName.contains("dto"))
                }

                if (disallowedTypes.isNotEmpty()) {
                    events.add(SimpleConditionEvent.violated(parameterTypes, "Method $parameterTypes has disallowed parameter types $disallowedTypes"))
                }
            }
        }

        val dtoParamRule = methods()
            .that().areDeclaredInClassesThat().resideInAnyPackage("..connectors..")
            .should(haveDtoOrPrimitiveParameterTypes)
            .`as`("Connector classes should only reference adapters and services")

        dtoParamRule.check(importedClasses)
    }

    @Test
    fun `Service methods should only reference domain classes or primitives and never DTOs`() {
        val importedClasses: JavaClasses = ClassFileImporter().importPackages(BASE_PACKAGE)

        val haveDomainOrPrimitiveParameterTypes = object : ArchCondition<HasParameterTypes>("should have domain classes or primitives as parameters but never DTOs") {
            override fun check(parameterTypes: HasParameterTypes, events: ConditionEvents) {
                val paramTypes = parameterTypes.rawParameterTypes

                val disallowedTypes = paramTypes.filter {
                    (it.packageName.contains(BASE_PACKAGE) && !it.packageName.contains("domain"))
                }

                if (disallowedTypes.isNotEmpty()) {
                    events.add(SimpleConditionEvent.violated(parameterTypes, "Method ${parameterTypes} has disallowed parameter types $disallowedTypes"))
                }
            }
        }

        val serviceParamRule = methods()
            .that().areDeclaredInClassesThat().resideInAPackage("..service..")
            .should(haveDomainOrPrimitiveParameterTypes)

        serviceParamRule.check(importedClasses)
    }
}


