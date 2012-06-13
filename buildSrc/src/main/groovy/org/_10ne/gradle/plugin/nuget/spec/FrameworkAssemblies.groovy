package main.groovy.org._10ne.gradle.plugin.nuget.spec

/**
 * @author Noam Y. Tenne
 */
class FrameworkAssemblies {

    def List<FrameworkAssembly> frameworkAssemblies = [];

    def methodMissing(String name, args) {
        frameworkAssemblies << new FrameworkAssembly(name, ((args != null) && (args.length > 0)) ? args[0] : null)
    }

    def methodMissing(String name) {
        methodMissing(name, null)
    }

    class FrameworkAssembly {
        def String assemblyName;
        def String targetFramework;

        FrameworkAssembly(String assemblyName, String targetFramework) {
            this.assemblyName = assemblyName
            this.targetFramework = targetFramework
        }
    }
}
