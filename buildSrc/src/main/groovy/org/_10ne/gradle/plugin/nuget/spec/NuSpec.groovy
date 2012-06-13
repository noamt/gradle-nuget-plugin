package main.groovy.org._10ne.gradle.plugin.nuget.spec

/**
 * @author Noam Y. Tenne
 */
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
    def Dependencies dependencies
    def FrameworkAssemblies frameworkAssemblies
    def References references
    def Files files

    def dependencies(Closure cl) {
        dependencies = new Dependencies()
        cl.delegate = dependencies
        cl()
    }

    def frameworkAssemblies(Closure cl) {
        frameworkAssemblies = new FrameworkAssemblies()
        cl.delegate = frameworkAssemblies
        cl()
    }

    def references(Closure cl) {
        references = new References()
        cl.delegate = references
        cl()
    }

    def packageFiles(Closure cl) {
        files = new Files()
        cl.delegate = files
        cl()
    }
}
