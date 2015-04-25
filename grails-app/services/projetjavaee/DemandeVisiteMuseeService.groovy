package projetjavaee

/**
 * Created by Fred on 25/04/2015.
 */
import grails.transaction.Transactional

@Transactional
class DemandeVisiteMuseeService {
    DemandeVisiteMusee insertOrUpdateDemandeVisiteMusee(DemandeVisiteMusee demandeVisiteMusee, Musee unMusee, DemandeVisite uneDemandeVisite) {
        uneDemandeVisite.save(flush: true)
        unMusee.save(flush:true)
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
