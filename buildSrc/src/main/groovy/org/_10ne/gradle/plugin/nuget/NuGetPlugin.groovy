package main.groovy.org._10ne.gradle.plugin.nuget

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Noam Y. Tenne
 */
class NuGetPlugin implements Plugin<Project> {

    def void apply(Project project) {
        project.convention.plugins.nuGet = new NuGetConfig()
        project.task('generateNuSpec') << {
            new NuSpecGenerator().generateSpec(project)
        }

        project.task('nuPack') << {

        }
    }
}
