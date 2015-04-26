package projetjavaee



import spock.lang.*

/**
 *
 */
class MuseeServiceIntegrationSpec extends Specification {

    Adresse uneAdresse
    Gestionnaire unGestionnaire
    MuseeService museeService
    JeuTestService jeuTestService

    def setup() {
        uneAdresse =  new Adresse(numero:"20",rue: "jean jaures",codePostal: "31400",ville: "Toulouse" ).save()
        unGestionnaire = new Gestionnaire(nom: "mairie").save(flush: true)
    }

    void "test la suppression d'un musee"() {

        given:"un musee existant en base"
        Musee unMusee = new Musee(nom:"Archive municipale",horaireOuverture: "de 8h à 18h", telephone:"0534768965",accesMetro:"metro A",accesBus: "bus 178")
        museeService.insertOrUpdateMusee(unMusee ,uneAdresse,unGestionnaire)

        when:"on déclenche la suppression du musee"
        museeService.deleteMusee(unMusee)

        then:"le musee est supprimée de la base"
        Musee.findById(unMusee.id) == null

        and:"ni l'adresse, ni le gestionnaire ne sont supprimés"
        Adresse.findById(uneAdresse.id) != null
        Gestionnaire.findById(unGestionnaire.id) != null
    }

    void "test insertion ou mise à jour d'un Musee"() {
        given:"un musee"
        Musee unMusee = new Musee(nom:"Archive municipale",horaireOuverture: "de 8h à 18h",
                telephone:"0534768965",accesMetro:"metro A",accesBus: "bus 178")

        and: "une adresse"
        uneAdresse =  new Adresse(numero:"20",rue: "jean jaures",codePostal: "31400",ville: "Toulouse" )

        and: "un gestionnaire"
        unGestionnaire = new Gestionnaire(nom: "mairie")

        when: "on tente de répercuter en base la création ou la modification du musee"
        Musee resMusee = museeService.insertOrUpdateMusee(unMusee,uneAdresse,unGestionnaire)

        then: "le musee resultant pointe sur le musee initale"
        resMusee == unMusee

        and:"le musee résultant n'a pas d'erreur"
        !resMusee.hasErrors()

        and:"le musee résultant a un id"
        resMusee.id

        and:"le musee est bien presente en base"
        Musee.findById(resMusee.id) != null

        and: "le musee a les bon attibut passé en paramètre"
        resMusee.adresse == uneAdresse
        resMusee.gestionnaire == unGestionnaire
    }

    void "test du moteur de recherche sur les musees"() {

        given:" une liste de musees "
        jeuTestService

        when:"on cherche les musees dont le nom contient toulouse "
        List<Musee> resMusee = museeService.searchMusee("Toulouse",null,null)

        then:"on récupère uniquement les 6 musees "
        resMusee.size() == 6
        resMusee*.id.contains(jeuTestService.musee1.id)
        resMusee*.id.contains(jeuTestService.musee4.id)
        resMusee*.id.contains(jeuTestService.musee4.id)
        resMusee*.id.contains(jeuTestService.musee6.id)
        resMusee*.id.contains(jeuTestService.musee9.id)
        resMusee*.id.contains(jeuTestService.musee10.id)

        when:"on cherche les musees dont le code postal est 31000"
        resMusee = museeService.searchMusee(null,"31000",null)

        then:"on récupère uniquement les 7 musees"
        resMusee.size() == 7
        resMusee*.id.contains(jeuTestService.musee2.id)
        resMusee*.id.contains(jeuTestService.musee3.id)
        resMusee*.id.contains(jeuTestService.musee4.id)
        resMusee*.id.contains(jeuTestService.musee7.id)
        resMusee*.id.contains(jeuTestService.musee8.id)
        resMusee*.id.contains(jeuTestService.musee10.id)
        resMusee*.id.contains(jeuTestService.musee12.id)

        when:"on cherche les musee dont le nom de la rue contient 'du' "
        resMusee = museeService.searchMusee(null,null,"du")

        then:"on recupère les 2 musees"
        resMusee.size() == 2
        resMusee*.id.contains(jeuTestService.musee10.id)
        resMusee*.id.contains(jeuTestService.musee11.id)

        when:"on cherche les musee sur lesquelles nom contient 'MUSEE' et le code postal est 31000"
        resMusee = museeService.searchMusee("MUSEE","31000",null)

        then:"on récupère les 4 musees "
        resMusee.size() == 4
        resMusee*.id.contains(jeuTestService.musee7.id)
        resMusee*.id.contains(jeuTestService.musee8.id)
        resMusee*.id.contains(jeuTestService.musee10.id)
        resMusee*.id.contains(jeuTestService.musee12.id)

        when:"on cherche les musees dont le nom de l'activité contient 'zzz' "
        resMusee = museeService.searchMusee("zzz",null,null)

        then: "on ne récupère aucune inscription"
        resMusee.size() == 0

        when:"on positionne tous les critères à null"
        resMusee = museeService.searchMusee(null, null, null)

        then: "on récupère toutes les inscriptions"
        resMusee.size() == 12
        resMusee*.id.contains(jeuTestService.musee1.id)
        resMusee*.id.contains(jeuTestService.musee2.id)
        resMusee*.id.contains(jeuTestService.musee3.id)
        resMusee*.id.contains(jeuTestService.musee4.id)
        resMusee*.id.contains(jeuTestService.musee5.id)
        resMusee*.id.contains(jeuTestService.musee6.id)
        resMusee*.id.contains(jeuTestService.musee7.id)
        resMusee*.id.contains(jeuTestService.musee8.id)
        resMusee*.id.contains(jeuTestService.musee9.id)
        resMusee*.id.contains(jeuTestService.musee10.id)
        resMusee*.id.contains(jeuTestService.musee11.id)
        resMusee*.id.contains(jeuTestService.musee12.id)
    }
}
