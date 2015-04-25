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
                                              String unAccesBus , Adresse uneAdresse,Gestionnaire unGestionnaire) {

        given: "un Musee initialise correctement"
        Musee unMusee = new Musee(nom: unNom, horaireOuverture: uneHoraireOuverture
                , telephone: unTelephone, accesMetro: unAccesMetro, accesBus: unAccesBus , adresse: uneAdresse
                , gestionnaire: unGestionnaire)

        expect: "le mussee est valide"
        unMusee.validate() == true

        where:

        unNom                   | uneHoraireOuverture  | unTelephone  | unAccesMetro | unAccesBus | uneAdresse   | unGestionnaire
        "Musee des compagnons"  | "de 8h à 17h"        | "0568998767" | "metro B"    | "bus 178"  | Mock(Adresse)| Mock(Gestionnaire)
        "Archive municipale"    | "de 8h à 18h"        | "0534768965" | "metro A"    | "bus 178"  | Mock(Adresse)| Mock(Gestionnaire)

    }


    @Unroll
    void "test l' invalidite d'un Musee invalide"(String unNom, String uneHoraireOuverture,
                                              String unTelephone , String unAccesMetro,
                                              String unAccesBus , Adresse uneAdresse,Gestionnaire unGestionnaire) {

        given: "un Musee initialise de maniere non valide"
        Musee unMusee = new Musee(nom: unNom, horaireOuverture: uneHoraireOuverture
                , telephone: unTelephone, accesMetro: unAccesMetro, accesBus: unAccesBus , adresse: uneAdresse
                , gestionnaire: unGestionnaire )

        expect: "le mussee est invalide"
        unMusee.validate() == false

        where:

        unNom                   | uneHoraireOuverture  | unTelephone  | unAccesMetro | unAccesBus | uneAdresse   | unGestionnaire
        null                    | "de 8h à 17h"        | "0568998767" | "metro B"    | "bus 178"  | Mock(Adresse)| Mock(Gestionnaire)
        ""                      | "de 8h à 18h"        | "0534768965" | "metro A"    | "bus 178"  | Mock(Adresse)| Mock(Gestionnaire)
        "Musee des compagnons"  | null                 | "0568998767" | "metro B"    | "bus 178"  | Mock(Adresse)| Mock(Gestionnaire)
        "Archive municipale"    | ""                   | "0534768965" | "metro A"    | "bus 178"  | Mock(Adresse)| Mock(Gestionnaire)
        "Archive municipale"    | "de 8h à 18h"        | null         | "metro A"    | "bus 178"  | Mock(Adresse)| Mock(Gestionnaire)
        "Archive municipale"    | "de 8h à 18h"        | "0534768965" | null         | "bus 178"  | Mock(Adresse)| Mock(Gestionnaire)
        "Archive municipale"    | "de 8h à 18h"        | "0534768965" | "metro A"    | null       | Mock(Adresse)| Mock(Gestionnaire)
        "Archive municipale"    | "de 8h à 18h"        | "0534768965" | "metro A"    | "bus 178"  | null         | Mock(Gestionnaire)
        "Archive municipale"    | "de 8h à 18h"        | "0534768965" | "metro A"    | "bus 178"  | Mock(Adresse)| null

    }




}
