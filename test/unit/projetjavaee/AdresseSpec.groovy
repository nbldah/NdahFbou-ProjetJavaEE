package projetjavaee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Adresse)
class AdresseSpec extends Specification {



    @Unroll
    void "test la validite d'une Adresse valide"(String unNumero, String uneRue, int unCodePostal , String uneVille) {

        given: "une adresse initialise correctement"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille)

        expect: "l'adresse est valide"
        adresse.validate() == true

        where:
        unNumero | uneRue                | unCodePostal | uneVille
        "4"      | "Auguste Perret"      | 31400        | "Toulouse"
        "TOUT"   | "Jean Jaques Rousseau"| 31200        | "Toulouse"
        "46"     | "Route de Narbonne"   | 31500        | "Toulouse"

    }


    @Unroll
    void "test l'invalidite d'une adresse non valide"(String unNumero, String uneRue, int unCodePostal , String uneVille) {

        given: "une adresse initialisé de maniere non valide"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille)

        expect: "l'adresse est invalide"
        adresse.validate() == false


        where:

        unNumero | uneRue                | unCodePostal | uneVille
        null     | "Auguste Perret"      | 31400      | "Toulouse"
        "TOUT"   | null                  | 31200      | "Toulouse"
        "140"    | "Route de Narbonne"   |  0         | "Toulouse"
        "4"      | "Auguste Perret"      | 31400      | null
        "TOUT"   | "Jean Jaques Rousseau"| 92500      | "Toulouse"
        "46"     | "Route de Narbonne"   | 31500      | "Paris"
        "19"     | ""                    | 31500      | "Toulouse"
        "44"     | "Auguste Perret"      | 31400      | ""


    }

}
