package main.groovy.org._10ne.gradle.plugin.nuget

import org.gradle.api.Plugin
import org.gradle.api.Project
import main.groovy.org._10ne.gradle.plugin.nuget.spec.*

/**
 * @author Noam Y. Tenne
 */
class NuGetPlugin implements Plugin<Project> {

    def void apply(Project project) {
        project.extensions.create('nuGet', NuGetConfig)
        project.nuGet.extensions.create('nuSpec', NuSpec)

        [dependencies: Dependency, frameworkAssemblies: FrameworkAssembly, references: Reference, files: File].each {
            project.nuGet.nuSpec.extensions."$it.key" = project.container(it.value)
        }

        project.task('generateNuSpec') << {
            new NuSpecGenerator().generateSpec(project)
        }

        project.task('nuPack') << {

        }
    }
}
