package projetjavaee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Musee)
class MuseeSpec extends Specification {

    @Unroll
    void "test la validite d'un Musee valide"(String unNom, String uneHoraireOuverture,
                                              String unTelephone , String unAccesMetro,
                                              String unAccesBus , Adresse uneAdresse) {

        given: "un Musee initialise correctement"
        Musee unMusee = new Musee(nom: unNom, horaireOuverture: uneHoraireOuverture
                , telephone: unTelephone, accesMetro: unAccesMetro, accesBus: unAccesBus , adresse: uneAdresse )

        expect: "le mussee est valide"
        unMusee.validate() == true

        where:

        unNom                   | uneHoraireOuverture  | unTelephone  | unAccesMetro | unAccesBus | uneAdresse
        "Musee des compagnons"  | "de 8h à 17h"        | "0568998767" | "metro B"    | "bus 178"  | Mock(Adresse)
        "Archive municipale"    | "de 8h à 18h"        | "0534768965" | "metro A"    | "bus 178"  | Mock(Adresse)


    }




}
