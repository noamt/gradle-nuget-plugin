package main.groovy.org._10ne.gradle.plugin.nuget.spec

/**
 * @author Noam Y. Tenne
 */
class DependencyGroup {

    String name
    String targetFramework

    public DependencyGroup(String name) {
        this.name = name
    }
}
