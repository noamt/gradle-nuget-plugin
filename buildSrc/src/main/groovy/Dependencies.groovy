package main.groovy

/**
 * @author Noam Y. Tenne
 */
class Dependencies {
    def List<Dependency> dependencies = [];

    def methodMissing(String name, args) {
        dependencies << new Dependency(name, ((args != null) && (args.length > 0)) ? args[0] : null)
    }

    def methodMissing(String name) {
        methodMissing(name, null)
    }

    class Dependency {
        def String id;
        def String version;

        Dependency(String id, String version) {
            this.id = id
            this.version = version
        }
    }
}
