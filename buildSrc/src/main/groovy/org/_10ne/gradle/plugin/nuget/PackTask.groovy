package main.groovy.org._10ne.gradle.plugin.nuget

import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.bundling.Zip

/**
 * @author Noam Y. Tenne
 */
class PackTask extends Zip {

    PackTask() {
        baseName = project.nuGet.nuSpec.id
        version = project.nuGet.nuSpec.version
        extension = 'nupkg'
        destinationDir = new java.io.File(project.buildDir, 'distributions')
        dependsOn('generateNuSpec')
        from "${project.buildDir}/nuget-plugin/${project.nuGet.nuSpec.id}.nuspec"
    }

    @TaskAction
    def pack() {

    }
}
