package projetjavaee

import grails.transaction.Transactional

/**
 * Created by Nabil on 25/04/15.
 */

@Transactional
class MuseeService {

    def insertOrUpdateMusee(Musee unMusee, Adresse uneAdresse ,Gestionnaire unGestionnaire )
    {
        unMusee.adresse = uneAdresse
        unMusee.gestionnaire = unGestionnaire

        uneAdresse.save(flush: true)
        unGestionnaire.save(flush: true)
        unMusee.save(flush: true)

        unMusee
    }

    def deleteMusee(Musee unMusee) {
        unMusee.delete(flush: true)
    }


    List<Musee> searchMusee(String nomMusee, String codePostal, String rueMusee) {
        //nomMusee ? null : ""
        //rueMusee ? null : ""

        def criteria = Musee.createCriteria()

        List<Musee> result = criteria.list {
            if (nomMusee) {
                ilike 'nom', "%${nomMusee}%"
            }

            if (codePostal) {
                adresse {
                    eq 'codePostal', "%${codePostal}%"
                }
            }

            if (rueMusee) {
                adresse {
                    ilike 'rue', "${rueMusee}"
                }
            }
        }
        result
    }


}
