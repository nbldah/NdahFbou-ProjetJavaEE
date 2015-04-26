package projetjavaee

/**
 * Created by Fred on 25/04/2015.
 */
import grails.transaction.Transactional

@Transactional
class DemandeVisiteMuseeService {

    MuseeService museeService

    DemandeVisiteMusee insertOrUpdateDemandeVisiteMusee(DemandeVisiteMusee demandeVisiteMusee, Musee unMusee, DemandeVisite uneDemandeVisite) {
        museeService.insertOrUpdateMusee(unMusee, unMusee.adresse, unMusee.gestionnaire)
        uneDemandeVisite.save(flush: true)
        demandeVisiteMusee.musee = unMusee
        demandeVisiteMusee.demandeVisite = uneDemandeVisite
        demandeVisiteMusee.dateDemande = new Date()
        demandeVisiteMusee.save(flush: true)
        demandeVisiteMusee
    }

    def deleteDemandeVisiteMusee(DemandeVisiteMusee demandeVisiteMusee) {
        if (demandeVisiteMusee) {
            demandeVisiteMusee.delete()
        }
    }
}
