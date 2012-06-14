package main.groovy.org._10ne.gradle.plugin.nuget

import groovy.xml.MarkupBuilder
import groovy.xml.MarkupBuilderHelper
import org.gradle.api.Project

/**
 * @author Noam Y. Tenne
 */
class NuSpecGenerator {

    public void generateSpec(Project project) {
        def specWriter = new StringWriter()
        def builder = new MarkupBuilder(specWriter)

        configureMarkupBuilder(builder)

        builder.'package'(xmlns: 'http://schemas.microsoft.com/packaging/2011/08/nuspec.xsd') {
            def nuSpec = project.nuGet.nuSpec
            metadata {
                id(nuSpec.id)
                version(nuSpec.version)
                title(nuSpec.title)
                authors(nuSpec.authors)
                owners(nuSpec.owners)
                licenseUrl(nuSpec.licenseUrl)
                projectUrl(nuSpec.projectUrl)
                iconUrl(nuSpec.iconUrl)
                requireLicenseAcceptance(nuSpec.requireLicenseAcceptance)
                description(nuSpec.description)
                summary(nuSpec.summary)
                releaseNotes(nuSpec.releaseNotes)
                copyright(nuSpec.copyright)
                language(nuSpec.language)
                tags(nuSpec.tags)

                if (nuSpec.dependencies) {
                    dependencies {
                        nuSpec.dependencies.each { dep ->
                            dependency(id: dep.name, version: dep.version)
                        }
                    }
                }

                if (nuSpec.frameworkAssemblies) {
                    frameworkAssemblies {
                        nuSpec.frameworkAssemblies.each { assembly ->
                            frameworkAssembly(assemblyName: assembly.name,
                                    targetFramework: assembly.targetFramework)
                        }
                    }
                }

                if (nuSpec.references) {
                    references {
                        nuSpec.references.each { ref ->
                            reference(file: ref.name)
                        }
                    }
                }
            }
            if (nuSpec.files) {
                files {
                    nuSpec.files.each { fileElement ->
                        name(src: fileElement.name, target: fileElement.target, exclude: fileElement.exclude)
                    }
                }
            }
        }

        specWriter.flush()
        String specContent = specWriter.toString()
        println 'Spec content is:'
        println specContent
        writeSpecFile(project, specContent)
    }

    private void configureMarkupBuilder(MarkupBuilder builder) {
        builder.omitEmptyAttributes = true
        builder.omitNullAttributes = true
        new MarkupBuilderHelper(builder).xmlDeclaration(version: '1.0')
    }

    private void writeSpecFile(Project project, String specContent) {
        File outDir = new File(project.getBuildDir(), 'nuget-plugin')
        outDir.mkdirs()
        File specFile = new File(outDir, "${project.getName()}.nuspec")
        specFile.text = specContent
        println "NuSpec File written to: ${specFile.absolutePath}"
    }
}
