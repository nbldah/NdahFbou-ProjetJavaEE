package projetjavaee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisiteMusee)
class DemandeVisiteMuseeSpec extends Specification {


    @Unroll
    void "test la validite d'une demande de visite d'un musée valide"() {

        given:"un musee et une activité"

        Musee unMusee = Mock(Musee)
        DemandeVisite uneDemandeVisite = Mock(DemandeVisite)

        and:"une date"
        Date now = new Date()

        when: "une demande de visite de musee est cree"
        DemandeVisiteMusee uneDemandeVisteMusee = new DemandeVisiteMusee(musee: unMusee, demandeVisite: uneDemandeVisite, dateDemande: now)

        then: "la demande de visite de musee est valide"
        uneDemandeVisteMusee.validate() == true

        and: "les propietes demande de visite de musee sont correctement mises a jour"
        uneDemandeVisteMusee.musee == unMusee
        uneDemandeVisteMusee.demandeVisite == uneDemandeVisite
        uneDemandeVisteMusee.dateDemande != null

    }


    @Unroll
    void "test l'invalidite d'une demande de visite de musee non valide"() {

        given:"un musee et une activité"
        Musee unMusee = Mock(Musee)
        DemandeVisite uneDemandeVisite = Mock(DemandeVisite)

        and:"une date"
        Date now = new Date()

        when: "une demande de visite de musee sans musee est créée"
        DemandeVisiteMusee uneDemandeVisteMusee = new DemandeVisiteMusee(musee: null, demandeVisite: uneDemandeVisite, dateDemande: now)

        then: "la demande de visite de musee n'est pas validée"
        uneDemandeVisteMusee.validate() == false

        when: "une demande de visite de musee sans demande de viste est créée"
        uneDemandeVisteMusee = new DemandeVisiteMusee(musee: unMusee, demandeVisite: null, dateDemande: now)

        then: "l'inscription n'est pas validée"
        uneDemandeVisteMusee.validate() == false

        when: "une inscription sans date de demande"
        uneDemandeVisteMusee = new DemandeVisiteMusee(musee: unMusee, demandeVisite: uneDemandeVisite, dateDemande: null)

        then: "l'inscription n'est pas validée"
        uneDemandeVisteMusee.validate() == false
    }


}
