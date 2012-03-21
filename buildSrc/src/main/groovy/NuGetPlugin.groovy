package main.groovy

import groovy.xml.MarkupBuilder
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Noam Y. Tenne
 */
class NuGetPlugin implements Plugin<Project> {

    def void apply(Project project) {
        project.convention.plugins.nuspec = new NuSpec()
        project.task('generateNuSpec') << {
            def specWriter = new StringWriter()
            def builder = new MarkupBuilder(specWriter)
            builder.root {
                'package' {
                    def nuspec = project.convention.plugins.nuspec
                    metadata {
                        id(nuspec.id)
                        version(nuspec.version)
                        authors(nuspec.authors)
                        owners(nuspec.owners)
                        licenseUrl(nuspec.licenseUrl)
                        projectUrl(nuspec.projectUrl)
                        iconUrl(nuspec.iconUrl)
                        requireLicenseAcceptance(nuspec.requireLicenseAcceptance)
                        description(nuspec.description)
                        tags(nuspec.tags)
                    }
                }
            }
            specWriter.flush()
            def specContent = specWriter.toString()
            println 'Spec content is:'
            println specContent
            def tempSpec = File.createTempFile('generated', 'nuspec')
            tempSpec.text = specContent
            println "Written spec into: ${tempSpec.absolutePath}"
        }
    }

    class NuSpec {
        def String id = 'Id'
        def String version = 'version'
        def String title = 'title'
        def String authors = 'authors'
        def String owners = 'owners'
        def String licenseUrl = 'licenseUrl'
        def String projectUrl = 'projectUrl'
        def String iconUrl = 'iconUrl'
        def boolean requireLicenseAcceptance = false
        def String description = 'description'
        def String summary = 'summary'
        def String releaseNotes = 'releaseNotes'
        def String copyright = 'copyright'
        def String language = 'en-US'
        def String tags = 'tags'
        //def Closure packageDependencies;
        //def Closure frameworkAssemblies;
        //def Closure references;

        def nuspec(Closure cl) {
            cl.delegate = this
            cl()
        }
    }
}
