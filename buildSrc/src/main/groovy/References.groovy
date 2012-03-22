package main.groovy

/**
 * @author Noam Y. Tenne
 */
class References {
    def List<Reference> references = [];

    def propertyMissing(String name, value) {
        references << new Reference(name)
    }

    class Reference {
        def String file;

        Reference(String file) {
            this.file = file
        }
    }
}
