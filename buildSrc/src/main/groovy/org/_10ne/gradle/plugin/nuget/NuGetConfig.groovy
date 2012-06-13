package main.groovy.org._10ne.gradle.plugin.nuget

import main.groovy.org._10ne.gradle.plugin.nuget.spec.NuSpec

/**
 * @author Noam Y. Tenne
 */
class NuGetConfig {

    NuSpec nuSpec

    def nuGet(Closure cl) {
        cl.delegate = this
        cl()
    }

    def nuSpec(Closure cl) {
        nuSpec = new NuSpec()
        cl.delegate = nuSpec
        cl()
    }
}
