package projetjavaee



import spock.lang.*

/**
 *
 */
class DemandeVisiteMuseeServiceIntegrationSpec extends Specification {

    Musee unMusee
    DemandeVisite uneDemandeVisite

    def demandeVisiteMuseeService

    def setup() {
        unMusee = new Musee(nom: "ARCHIVES MUNICIPALES TOULOUSE", horaireOuverture: "Ouvert du lundi au vendredi de 9h à 17h"
                , telephone: "0561616333", accesMetro: "Roseraie (A)",
                accesBus: "36, 38" , adresse: new Adresse(numero: "4", rue: "Auguste Perret", codePostal: 31400, ville: "Toulouse").save())

        unMusee.save()



        uneDemandeVisite = new DemandeVisite(dateDebutPeriode: new Date(2015,
                4, 15), dateFinPeriode: new Date(2015, 4, 20), nbPersonnes: 5, statut: "En cours de traitement")

        uneDemandeVisite.save()
    }

    void "test la creation ou la mise a jour d'une demandevisitemusee"() {

        given: "une demandeVisiteMusee"
        DemandeVisiteMusee uneDemandeVisiteMusee = new DemandeVisiteMusee(musee: unMusee, demandeVisite: uneDemandeVisite)

        when: "on insert ou met à jour une demande de visite musee"
        DemandeVisiteMusee resDVM = demandeVisiteMuseeService.insertOrUpdateDemandeVisiteMusee(uneDemandeVisiteMusee, unMusee, uneDemandeVisite)

        then: "la demande de visite musee est bien celle inseree"
        resDVM == uneDemandeVisiteMusee

        and:"la demande de visite musee a bien un id"
        uneDemandeVisiteMusee.id != null

        and:"elle est valide"
        uneDemandeVisiteMusee.validate()

        and:"elle est bien stockée en base"
        DemandeVisiteMusee.findById(uneDemandeVisiteMusee.id) != null

        and :"les propriétes sont mises à jours comme attendues"
        uneDemandeVisiteMusee.musee == unMusee
        uneDemandeVisiteMusee.demandeVisite == uneDemandeVisite
        uneDemandeVisiteMusee.dateDemande != null
    }

    void "test de la suppression d'une demande de visite musee"() {

        given:"une demande de visite musee existante en base"
        DemandeVisiteMusee uneDemandeVisiteMusee = new DemandeVisiteMusee(musee: unMusee, demandeVisite: uneDemandeVisite)
        demandeVisiteMuseeService.insertOrUpdateDemandeVisiteMusee(uneDemandeVisiteMusee, unMusee, uneDemandeVisite)

        when:"on declenche la suppression de l'inscription"
        demandeVisiteMuseeService.deleteDemandeVisiteMusee(uneDemandeVisiteMusee)

        then:"la demande de viste musee est supprimee de la base"
        DemandeVisiteMusee.findById(uneDemandeVisiteMusee.id) == null

        and:"ni le musee ni la demande de visite ne sont supprimes"
        Musee.findById(unMusee.id) != null
        DemandeVisite.findById(uneDemandeVisite.id) != null

    }
}
