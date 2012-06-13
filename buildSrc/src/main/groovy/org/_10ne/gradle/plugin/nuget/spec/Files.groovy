package main.groovy.org._10ne.gradle.plugin.nuget.spec

/**
 * @author Noam Y. Tenne
 */
class Files {

    def List<File> files = [];

    def methodMissing(String name, args) {
        files << new File(name, argByIndex(args, 0), argByIndex(args, 1))
    }

    def argByIndex(args, i) {
        if (args && args.length >= (i + 1)) return args[i];
        return null
    }

    def propertyMissing(String name, value) {
        methodMissing(name, null)
    }

    class File {

        def String src
        def String target
        def String exclude

        File(String src, String target, String exclude) {
            this.src = src
            this.target = target
            this.exclude = exclude
        }
    }
}
