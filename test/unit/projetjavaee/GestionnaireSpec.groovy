package projetjavaee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Gestionnaire)
class GestionnaireSpec extends Specification {

    @Unroll
    void "test la validite d'un gestionnaire valide"(String unNom) {

        given: "un gestionnaire initilialise correctement"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le gestionnaire est valide"
        gestionnaire.validate() == true

        where:
        unNom = "dupont"

    }

    @Unroll
    void "test l'invalidite d'un gestionnaire non valide"(String unNom) {

        given: "un gestionnaire initialise de maniere non valide"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le gestionnaire est invalide"
        gestionnaire.validate() == false

        where:
        unNom = null

    }

    @Unroll
    void "test de l'affichage"(String unNom) {

        given: "un gestionnaire initilialise correctement"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le gestionnaire retourne une chaine valide"
        gestionnaire.toString() == unNom
        gestionnaire.toString().size() != 0

        where:
        unNom = "dupont"

    }



}
