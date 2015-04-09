package projetjavaee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisite)
class DemandeVisiteSpec extends Specification {

    @Unroll
    void "test la validite d'une demande de visite valide"(String unCode,Date uneDateDebut, Date uneDateFin, int unNbPersonnes, String unStatut) {

        given: "une demande de visite initialise avec une date de debut non vide , une date de fin de période non vide, un nombre de personnes présente inférieur à 6"
        DemandeVisite demandeVisite = new DemandeVisite(code: unCode, dateDebutPeriode: uneDateDebut, dateFinPeriode: uneDateFin, nbPersonnes: unNbPersonnes, statut: unStatut)


        expect: "la demande de visite est valide"
        demandeVisite.validate() == true

        where:
        unCode | uneDateDebut          | uneDateFin            | unNbPersonnes | unStatut
        _ | new Date(2015, 4, 15) | new Date(2015, 4, 20) | 5 | _
    }

    @Unroll
    void "test l'invalidite d'une demande de visite non valide"(String unCode, Date uneDateDebut, Date uneDateFin, int unNbPersonnes, String unStatut) {

        given: "une demande de visite initialise de maniere non valide"
        DemandeVisite demandeVisite = new DemandeVisite(code: unCode, dateDebutPeriode: uneDateDebut, dateFinPeriode: uneDateFin, nbPersonnes: unNbPersonnes, statut: unStatut)

        expect: "la demande de visite est invalide"
        demandeVisite.validate() == false

        where:
        unCode | uneDateDebut          | uneDateFin            | unNbPersonnes | unStatut
        _      | new Date(2015, 4, 15) | new Date(2015, 4, 20) | 0             | _
        _      | new Date(2015, 4, 15) | new Date(2015, 4, 20) | 7             | _
        _      | null                  | new Date(2015, 4, 20) | 5             | _
        _      | new Date(2015, 4, 15) | null                  | 5             | _
        _      | new Date(2015, 4, 20) | new Date(2015, 4, 15) | 5             | _
    }
}
